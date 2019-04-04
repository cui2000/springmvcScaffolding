package demo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class AutoCreateBeanDao {

	private static String packageName = "demo/dao";

	private static String packageName_bean = "demo/bean";

	private static boolean skipExist = true; // 跳过已存在的文件

	public static void main(String[] args) {
		createBeanDaoFile();
		createBeanDaoImplFile();
	}

	private static void createBeanDaoFile() {
		String path_bean = System.getProperty("user.dir") + "/src/main/java/" + packageName_bean;
		File folder_bean = new File(path_bean);
		if (!folder_bean.exists()) {
			return;
		}
		String path_dao = System.getProperty("user.dir") + "/src/main/java/" + packageName + "/";
		File folder = new File(path_dao);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String[] beanFileNames = folder_bean.list();
		Arrays.asList(beanFileNames).stream().forEach((beanFileName) -> {
			String beanName = beanFileName.substring(0, beanFileName.indexOf("."));
			String fileName = path_dao + "I" + beanName + "Dao.java";
			File file = new File(fileName);
			if (skipExist && file.exists()) {
				return;
			}
			System.out.println(fileName);
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(String.format("package %s;\r\n", packageName.replaceAll("/", ".")));
				bufferedWriter.newLine();
				bufferedWriter.write(String.format("import %s.%s;\r\n", packageName_bean.replaceAll("/", "."), beanName));
				bufferedWriter.newLine();
				bufferedWriter.write(String.format("public interface I%sDao extends IBaseDao<%<s> {\r\n", beanName));
				bufferedWriter.newLine();
				bufferedWriter.write("}");
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	private static void createBeanDaoImplFile() {
		String path_bean = System.getProperty("user.dir") + "/src/main/java/" + packageName_bean;
		File folder_bean = new File(path_bean);
		if (!folder_bean.exists()) {
			return;
		}
		String packageName_impl = packageName + "/impl";
		String path_dao_impl = System.getProperty("user.dir") + "/src/main/java/" + packageName_impl + "/";
		File folder = new File(path_dao_impl);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String[] beanFileNames = folder_bean.list();
		Arrays.asList(beanFileNames).stream().forEach((beanFileName) -> {
			String beanName = beanFileName.substring(0, beanFileName.indexOf("."));
			String fileName = path_dao_impl + beanName + "DaoImpl.java";
			File file = new File(fileName);
			if (skipExist && file.exists()) {
				return;
			}
			System.out.println(fileName);
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(String.format("package %s;\r\n", packageName_impl.replaceAll("/", ".")));
				bufferedWriter.newLine();
				bufferedWriter.write("import org.springframework.stereotype.Repository;\r\n");
				bufferedWriter.newLine();
				bufferedWriter.write(String.format("import %s.%s;\r\n", packageName_bean.replaceAll("/", "."), beanName));
				bufferedWriter.write(String.format("import %s.I%sDao;\r\n", packageName.replaceAll("/", "."), beanName));
				bufferedWriter.newLine();
				bufferedWriter.write(String.format("@Repository(\"%sDao\")\r\n", lowerOrUpperFirst(beanName, true)));
				bufferedWriter.write(String.format("public class %sDaoImpl extends BaseDaoImpl<%<s> implements I%<sDao {\r\n", beanName));
				bufferedWriter.newLine();
				bufferedWriter.write("}");
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

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

}
