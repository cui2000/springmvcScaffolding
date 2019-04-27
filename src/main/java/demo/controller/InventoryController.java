package demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import demo.bean.Actor;
import demo.bean.Customer;
import demo.bean.Film;
import demo.bean.Inventory;
import demo.bean.Staff;
import demo.service.IBusinessService;
import demo.service.ICustomerService;
import demo.service.IInventoryService;
import demo.util.HttpUtil;
import demo.util.PageEntity;

@Controller
public class InventoryController {

	private static Logger logger = LoggerFactory.getLogger(InventoryController.class);

	private final static String url_rentals_unreturn = "http://localhost:8108/demo/rental_records/unreturn";
	private final static String url_inventorys = "http://localhost:8108/demo/inventoryInStock";
	private final static String url_staffs = "http://localhost:8108/demo/staffs";
	private final static String url_film_rent = "http://localhost:8108/demo/film_rent";
	private final static String url_film_return = "http://localhost:8108/demo/film_return";

	@Autowired
	@Qualifier("inventoryService")
	private IInventoryService inventoryService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IBusinessService businessService;

	@RequestMapping("/demo")
	private ModelAndView toDemo() {
		return new ModelAndView("demo");
	}

	@RequestMapping(value = "/getActorData", method = RequestMethod.GET)
	@ResponseBody
	private PageEntity<Actor> getActorData(@RequestParam(defaultValue = "1") int page) {
		int rowSize = 20;
		int startRow = (page - 1) * rowSize;
		return new PageEntity<>(inventoryService.findActor(startRow, rowSize), inventoryService.getActorCount(), rowSize, page);
	}

	@RequestMapping(value = "/getCustomerData", method = RequestMethod.GET)
	@ResponseBody
	private PageEntity<Customer> getCustomerData(@RequestParam(defaultValue = "1") int page) {
		int rowSize = 20;
		int startRow = (page - 1) * rowSize;
		return new PageEntity<>(customerService.findCustomer(startRow, rowSize), customerService.getCustomerCount(), rowSize, page);
	}

	@RequestMapping(value = "/getFilmData", method = RequestMethod.GET)
	@ResponseBody
	private PageEntity<Film> getFilmData(@RequestParam(defaultValue = "1") int page) {
		int rowSize = 20;
		int startRow = (page - 1) * rowSize;
		return new PageEntity<>(inventoryService.findFilm(startRow, rowSize), inventoryService.getFilmCount(), rowSize, page);
	}

	// @RequestMapping(value = "/getInventoryData", method = RequestMethod.GET)
	// @ResponseBody
	// private PageEntity<Inventory> getInventoryData(@RequestParam(defaultValue
	// = "1") int page) {
	// int rowSize = 20;
	// int startRow = (page - 1) * rowSize;
	// return new PageEntity<>(inventoryService.findFilm(startRow, rowSize),
	// inventoryService.getFilmCount(), rowSize, page);
	// }

	@RequestMapping(value = "/getStaffData", method = RequestMethod.GET)
	@ResponseBody
	private PageEntity<Staff> getStaffData(@RequestParam(defaultValue = "1") int page) {
		int rowSize = 20;
		int startRow = (page - 1) * rowSize;
		return new PageEntity<>(businessService.findStaff(startRow, rowSize), businessService.getStaffCount(), rowSize, page);
	}

	private String rentalFilm(@RequestParam int customerId, @RequestParam int staffId, @RequestParam int inventoryId) {
		// 租金规则，超过一天1块钱，超过是指租借当天日期加上film的默认租期
		businessService.rentFilm(customerId, staffId, inventoryId, new Date());
		return "success";
	}

	// private String returnFilm(@RequestParam int rentalId) {
	// // 还电影
	// businessService.returnFilm(rentalId, new Date());
	// return "success";
	// }

	@RequestMapping(value = "/testRent")
	@ResponseBody
	private String testRent() {
		Date date = new Date();
		// 获取所有客户
		List<Customer> customers = customerService.findCustomer(0, 9999);

		// 获取所有库存记录
		List<Inventory> inventories = inventoryService.findInventoryInStock();

		logger.info("findInventoryInStock: {}", System.currentTimeMillis() - date.getTime());

		Map<Integer, Boolean> map_inventoryId_stock = new ConcurrentHashMap<>();
		inventories.parallelStream().forEach(inventory -> map_inventoryId_stock.put(inventory.getInventoryId(), false));
		// 获取所有员工
		List<Staff> staffs = businessService.findStaff(0, 999);

		// 按员工分组库存
		// Map<Integer, Integer> map_inventoryId_staffId = new
		// ConcurrentHashMap<>();
		// inventories.parallelStream().forEach((inventory) -> {
		// staffs.parallelStream().forEach((staff) -> {
		// map_inventoryId_staffId.put(inventory.getInventoryId(),
		// staff.getStaffId());
		// });
		// });
		logger.info("begin rent: {}", System.currentTimeMillis() - date.getTime());

		// 客户租借电影
		final AtomicInteger index = new AtomicInteger(0);
		Lock lock = new ReentrantLock();
		customers.parallelStream().forEach((customer) -> {
			boolean success = false;
			// 如果重试，锁加在这
			// lock.lock();
			// synchronized (index) {
			// 失败则重试
			while (!success) {
				Inventory inventory = inventories.get(index.get());
				staffs.forEach(staff -> {
					if (staff.getStoreId().equals(inventory.getStoreId())) {
						// 如果不重试，锁加在这
						// synchronized (index) {
						// 此方法应该加事务，确保数据正确
						lock.lock();
						boolean result = businessService.rentFilm(customer.getCustomerId(), staff.getStaffId(), inventory.getInventoryId(), date);
						lock.unlock();
						map_inventoryId_stock.put(inventory.getInventoryId(), result);
						if (result) {
							index.incrementAndGet();
						}
						logger.info("customerId: {} staffId: {} inventoryId: {} result: {} index: {} time: {}", customer.getCustomerId(), staff.getStaffId(), inventory.getInventoryId(), result,
								index.get(), System.currentTimeMillis() - date.getTime());
					}
					return;
					// }
				});
				success = map_inventoryId_stock.get(inventory.getInventoryId());
			}
			// }
			// lock.unlock();
		});
		if (logger.isDebugEnabled()) {
			logger.debug("debug : rent: {}", System.currentTimeMillis() - date.getTime());
		}
		logger.info("rent: {}", System.currentTimeMillis() - date.getTime());
		return "success";
		// 83899
		// 13800
		// sync 105253 lock 92128
		// 75328

	}

	@RequestMapping(value = "/rent")
	@ResponseBody
	private String rent() {

		Date date = new Date();
		// 获取所有客户
		List<Customer> customers = customerService.findCustomer(0, 9999);

		// 获取所有库存记录
		List<Inventory> inventories = inventoryService.findInventoryInStock();

		logger.info("findInventoryInStock: {}", System.currentTimeMillis() - date.getTime());

		Map<Integer, Boolean> map_inventoryId_stock = new ConcurrentHashMap<>();
		inventories.parallelStream().forEach(inventory -> map_inventoryId_stock.put(inventory.getInventoryId(), false));
		// 获取所有员工
		List<Staff> staffs = businessService.findStaff(0, 999);

		return rent(customers.get(0).getCustomerId(), staffs.get(0).getStaffId(), inventories.get(0).getInventoryId());
	}

	private String rent(int customerId, int staffId, int inventoryId) {
		Date date = new Date();
		Map<String, String> params = new HashMap<>();
		params.put("customerId", String.valueOf(customerId));
		params.put("staffId", String.valueOf(staffId));
		String result = HttpUtil.post(String.format("%s/%s", url_film_rent, inventoryId), params);

		logger.info("rent cost time: {} result: {}", System.currentTimeMillis() - date.getTime(), result);
		return result;
	}

	private String returnFilm(int rentalId) {
		Date date = new Date();
		String result = HttpUtil.post(String.format("%s/%s", url_film_return, rentalId));
		logger.info("return cost time: {} result: {}", System.currentTimeMillis() - date.getTime(), result);
		return result;
	}

	@RequestMapping(value = "/autoRent")
	@ResponseBody
	private String autoRent() {
		Date begin = new Date();
		ExecutorService executorService = Executors.newCachedThreadPool();
		//借电影
		executorService.execute(() -> {
			// 获取所有客户
			List<Customer> customers = customerService.findCustomer(0, 9999);

			// 获得员工记录
			String result_staffs = HttpUtil.get(url_staffs);
			JSONArray json_staffs = JSONArray.parseArray(result_staffs);
			logger.info("json_staffs -> {}", result_staffs);
			Map<Integer, Integer> map_storeId_staffId = json_staffs.parallelStream().collect(Collectors.toMap(o -> {
				return Integer.valueOf(((JSONObject) o).get("storeId").toString());
			}, o -> {
				return Integer.valueOf(((JSONObject) o).get("staffId").toString());
			}));

			// 获得库存记录
			String result_inventorys = HttpUtil.get(url_inventorys);
			JSONArray json_inventorys = JSONArray.parseArray(result_inventorys);

			List<Integer> inventoryIds = json_inventorys.parallelStream().map(o -> Integer.valueOf(((JSONObject) o).get("inventoryId").toString())).collect(Collectors.toList());
//			ArrayBlockingQueue<JSONObject> arrayBlockingQueue = new ArrayBlockingQueue();
			//这里如果用inventoryIds_unsafe，下面的代码会中途报错，因为用到了inventoryIds.size()获取随机数，而有多个线程在修改inventoryIds.remove(index)，执行完后会发现inventoryIds.size>0
//			List<Integer> inventoryIds = Collections.synchronizedList(inventoryIds_unsafe);
			Random random = new Random(3); //伪随机，重复生成的随机数一致
			Lock lock = new ReentrantLock(); //给inventoryIds操作加锁，避免多线程操作异常
			int count_inventoryIds = inventoryIds.size();
			customers.parallelStream().forEach(customer->{
				int customerId = customer.getCustomerId();
				//每人租三部
				for(int i=0;i<3;i++){
					if(inventoryIds.size()>0){
//						lock.lock();
//						int index = random.nextInt(inventoryIds.size());
						int inventoryId = inventoryIds.remove(0);
						rent(customerId, map_storeId_staffId.get(customer.getStoreId()), inventoryId);
//						lock.unlock();
						logger.info("inventoryIds.size -> {}", inventoryIds.size());
					}
				}
			});
			
			logger.info("rent cost total time -> {},expect inventoryIds.size -> {}, actual inventoryIds.size -> {}",System.currentTimeMillis()-begin.getTime(),count_inventoryIds-(customers.size()*3),inventoryIds.size());
			//lock rent, cost total time -> 322283
			//rent cost total time -> 52288
			
		});
		//还电影
		executorService.execute(() -> {
			Random r = new Random();
			// 获得租借记录
			String result_rentals = HttpUtil.get(url_rentals_unreturn);
			logger.info("json_inventorys -> {}", result_rentals);
			JSONArray json_rentals = JSONArray.parseArray(result_rentals);
			json_rentals.parallelStream().forEach(object->{
				JSONObject jsonObject = (JSONObject)object;
				int rentalId = Integer.valueOf(jsonObject.get("rentalId").toString());
				
				// 计算归还日期判断是否要多交钱
				// 租金规则，超过一天1块钱，超过是指租借当天日期加上film的默认租期
				Date d_rental = beginOfDate(getDateFromStr(jsonObject.get("rentalDate").toString()));

				Date d_return = beginOfDate(new Date());

				int diff = r.nextInt(8); //8天随机
				
				long days = (d_return.getTime() - d_rental.getTime()) / (1000 * 3600 * 24);
				
				if(days>diff){
					logger.info("diff -> {},days -> {}",diff,days);
					returnFilm(rentalId);
				}
			});
			logger.info("return cost total time -> {}",System.currentTimeMillis()-begin.getTime());
		});
		executorService.shutdown();
		logger.info("线程执行，我先返回，花了 -> {} 毫秒",System.currentTimeMillis()-begin.getTime());
		return "start";
	}

	private Date beginOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	private Date getDateFromStr(String date_str){
		Calendar c = Calendar.getInstance();
		String[] strs = date_str.substring(0, date_str.indexOf("T")).split("-");
		c.set(Calendar.YEAR, Integer.valueOf(strs[0]));
		c.set(Calendar.MONTH, Integer.valueOf(strs[1]));
		c.set(Calendar.DATE, Integer.valueOf(strs[2]));
		return c.getTime();
	}

}
