package demo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoCreateBean {

	private static String repo_name = "sakila";

	private static String packageName = "demo/bean";

	private static boolean skipExist = false; // 跳过已存在的文件

	private static AutoCreateBean autoCreateBean = new AutoCreateBean();

	public static void main(String[] args) {
		String path = "file:F:/study/java/springmvcScaffolding/src/main/webapp/WEB-INF/config/spring/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sf.openSession();
		List<String> tableNames = getTableNames(session);
		System.out.println(tableNames.size());

		for (String tableName : tableNames) {
			System.out.println(tableName);
			BeanInfo beanInfo = getBeanInfo(session, tableName);
			createBeanFile(beanInfo);
		}
	}

	/**
	 * 获取表名列表
	 * 
	 * @param session
	 * @return
	 */
	private static List<String> getTableNames(Session session) {
		String sql = "select table_name from information_schema.tables where table_schema='" + repo_name + "' and table_type = 'base table';";
		Query query = session.createSQLQuery(sql);
		return query.getResultList();
	}

	/**
	 * 获取表对应实体信息
	 * 
	 * @param session
	 * @param tableName
	 * @return
	 */
	private static BeanInfo getBeanInfo(Session session, String tableName) {
		BeanInfo beanInfo = autoCreateBean.new BeanInfo(tableName);

		String sql = "select column_name,data_type,column_key from information_schema.columns where table_schema='" + repo_name + "' and table_name='" + tableName + "';";
		Query query = session.createSQLQuery(sql);
		List<ColumnInfo> columnInfos = (List<ColumnInfo>) query.getResultList().stream().map((column) -> {
			Object[] objects = (Object[]) column;
			return autoCreateBean.new ColumnInfo((String) objects[0], (String) objects[1], "PRI".equalsIgnoreCase((String) objects[2]));
		}).collect(Collectors.toList());
		return autoCreateBean.new BeanInfo(tableName, columnInfos);
	}

	/**
	 * 首字母大小写
	 * 
	 * @param str
	 * @param lower
	 * @return
	 */
	private static String lowerOrUpperFirst(String str, boolean lower) {
		char[] chars = str.toCharArray();
		if (lower) {
			if (chars[0] >= 'A' && chars[0] <= 'Z')
				chars[0] += 32;
		} else {
			if (chars[0] >= 'a' && chars[0] <= 'z') {
				chars[0] -= 32;
			}
		}
		return String.valueOf(chars);
	}

	private static void createBeanFile(BeanInfo beanInfo) {
		String path = System.getProperty("user.dir") + "/src/main/java/" + packageName + "/";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String fileName = path + beanInfo.name + ".java";
		File file = new File(fileName);
		if (skipExist && file.exists()) {
			return;
		}
		System.out.println(fileName);
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			writeBeanHeader(bufferedWriter, beanInfo);
			writeBeanBody(bufferedWriter, beanInfo);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据实体类信息生成引入的类
	 * 
	 * @param bufferedWriter
	 * @param beanInfo
	 * @throws IOException
	 */
	private static void writeBeanHeader(BufferedWriter bufferedWriter, BeanInfo beanInfo) throws IOException {
		bufferedWriter.write("package " + packageName.replace("/", ".") + ";\r\n");
		bufferedWriter.newLine();
		beanInfo.importClass.stream().sorted().forEach((importStr) -> {
			try {
				bufferedWriter.write(String.format("import %s;\r\n", importStr));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 根据实体类信息生成实体属性及方法
	 * 
	 * @param bufferedWriter
	 * @param beanInfo
	 * @throws IOException
	 */
	private static void writeBeanBody(BufferedWriter bufferedWriter, BeanInfo beanInfo) throws IOException {
		bufferedWriter.newLine();
		bufferedWriter.write(String.format("@Entity(name = \"%s\")\r\n", beanInfo.tableName));
		bufferedWriter.write(String.format("public class %s  implements Serializable {\r\n", beanInfo.name));
		bufferedWriter.write("\tprivate static final long serialVersionUID = 1L;\r\n");
		bufferedWriter.newLine();
		// 字段
		for (ColumnInfo columnInfo : beanInfo.columnInfos) {
			if (columnInfo.isPrimaryKey) {
				bufferedWriter.write("\t@Id\r\n");
			}
			bufferedWriter.write(String.format("\t@Column(name=\"%s\")\r\n", columnInfo.columnName));
			bufferedWriter.write(String.format("\tprivate %s %s;\r\n", columnInfo.fieldType, columnInfo.fieldName));
		}
		bufferedWriter.newLine();
		// getset方法
		for (ColumnInfo columnInfo : beanInfo.columnInfos) {
			bufferedWriter.write(String.format("\tpublic %s get%s(){\r\n", columnInfo.fieldType, lowerOrUpperFirst(columnInfo.fieldName, false)));
			bufferedWriter.write(String.format("\t\treturn %s;\r\n", columnInfo.fieldName));
			bufferedWriter.write("\t}\r\n");
			bufferedWriter.newLine();
			bufferedWriter.write(String.format("\tpublic void set%s(%s %s){\r\n", lowerOrUpperFirst(columnInfo.fieldName, false), columnInfo.fieldType, columnInfo.fieldName));
			bufferedWriter.write(String.format("\t\tthis.%s = %<s;\r\n", columnInfo.fieldName));
			bufferedWriter.write("\t}");
			bufferedWriter.newLine();
		}
		bufferedWriter.newLine();
		bufferedWriter.write("}");
	}

	/**
	 * 实体类信息
	 * 
	 * @author cuibo
	 *
	 */
	class BeanInfo {
		String name;
		String tableName;
		List<ColumnInfo> columnInfos = new ArrayList<>();
		Set<String> importClass = Stream.of("javax.persistence.Entity", "java.io.Serializable").collect(Collectors.toSet());

		public BeanInfo() {
		}

		public BeanInfo(String tableName) {
			this.tableName = tableName;
			setName(tableName);
		}

		public BeanInfo(String tableName, List<ColumnInfo> columnInfos) {
			this(tableName);
			this.columnInfos.addAll(columnInfos);
			importClass.addAll(columnInfos.stream().flatMap((columnInfo) -> {
				return columnInfo.importClass.stream();
			}).collect(Collectors.toSet()));
		}

		public void setName(String name) {
			String[] strs = name.split("_");
			StringBuilder sb = new StringBuilder();
			for (String str : strs) {
				sb.append(lowerOrUpperFirst(str, false));
			}
			this.name = sb.toString();
		}

		public void addColumnInfo(ColumnInfo columnInfo) {
			this.columnInfos.add(columnInfo);
			this.importClass.addAll(columnInfo.importClass);
		}
	}

	/**
	 * 字段信息
	 * 
	 * @author cuibo
	 *
	 */
	class ColumnInfo {
		String columnName; // 字段名称
		String columnType; // 字段类型
		String fieldName; // 字段名称
		String fieldType; // 字段类型
		boolean isPrimaryKey; // 是否主键
		List<String> importClass = Stream.of("javax.persistence.Column").collect(Collectors.toList()); // 字段类型对应的java类，如果不是java.lang包的类需要引入

		public ColumnInfo() {
		}

		public ColumnInfo(String columnName, String columnType, boolean isPrimaryKey) {
			this.columnName = columnName;
			this.columnType = columnType;
			setFieldName(columnName);
			setFieldType(columnType);
			this.isPrimaryKey = isPrimaryKey;
			if (this.isPrimaryKey) {
				importClass.add("javax.persistence.Id");
			}
		}

		public void setFieldName(String columnName) {
			String[] strs = columnName.split("_");
			StringBuilder sb = new StringBuilder();
			for (String str : strs) {
				sb.append(lowerOrUpperFirst(str, false));
			}
			this.fieldName = lowerOrUpperFirst(sb.toString(), true);
		}

		public void setFieldType(String columnType) {
			if (columnType.equalsIgnoreCase("bit")) {
				this.fieldType = "boolean";
			} else if (columnType.equalsIgnoreCase("tinyint")) {
				this.fieldType = "byte";
			} else if (columnType.equalsIgnoreCase("int") || columnType.equalsIgnoreCase("smallint") || columnType.equalsIgnoreCase("mediumint") || columnType.equalsIgnoreCase("year")) {
				this.fieldType = "int";
			} else if (columnType.equalsIgnoreCase("bigint")) {
				this.fieldType = "long";
			} else if (columnType.equalsIgnoreCase("float")) {
				this.fieldType = "double";
			} else if (columnType.equalsIgnoreCase("real")) {
				this.fieldType = "float";
			} else if (columnType.equalsIgnoreCase("decimal") || columnType.equalsIgnoreCase("numeric") || columnType.equalsIgnoreCase("money") || columnType.equalsIgnoreCase("smallmoney")) {
				this.fieldType = "BigDecimal";
				this.importClass.add("java.math.BigDecimal");
			} else if (columnType.equalsIgnoreCase("varchar") || columnType.equalsIgnoreCase("char") || columnType.equalsIgnoreCase("nvarchar") || columnType.equalsIgnoreCase("nchar")
					|| columnType.equalsIgnoreCase("text") || columnType.equalsIgnoreCase("enum") || columnType.equalsIgnoreCase("geometry") || columnType.equalsIgnoreCase("set")) {
				this.fieldType = "String";
			} else if (columnType.equalsIgnoreCase("datetime") || columnType.equalsIgnoreCase("date") || columnType.equalsIgnoreCase("timestamp")) {
				this.fieldType = "Date";
				this.importClass.add("java.util.Date");
			} else if (columnType.equalsIgnoreCase("image") || columnType.equalsIgnoreCase("blob")) {
				this.fieldType = "byte[]";
			} else {
				this.fieldType = columnType;
			}
		}
	}
}
