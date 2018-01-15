package com.yd.main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.yd.entity.Student;
import com.yd.service.IStudentService;
import com.yd.service.impl.StudentServiceeImpl;

/**
 * 展示数据 View ----> Service ----> Dao ----> db
 * 
 * view: 用来显示数据 Service: 用来提供不同的操作方法 (没有sql语句,只有对应的操作) Dao: 用来直接和数据库操作
 * (能看见sql语句) db: 数据库
 * 
 * @author john
 * @Date 2018年1月11日
 * @company 亚帝科技有限公司
 */
public class Test {
	private static IStudentService service = new StudentServiceeImpl();

	public static void main(String[] args) {

		show();
	}

	public static void show() {
		System.out.println("************欢迎来到学生信息系统************");
		System.out.println("************1.添加学生信息************");
		System.out.println("************2.更新学生信息************");
		System.out.println("************3.删除学生信息************");
		System.out.println("************4.查询单个学生信息************");
		System.out.println("************5.查询所有学生信息************");
		System.out.println("************6.退出学生系统************");

		Scanner input = new Scanner(System.in);
		System.out.println("请输入你想输入的菜单编号进行操作:");
		int num = input.nextInt();
		// 增删查改学生对象
		String sname;
		int sage;
		String ssex;
		String sdate;
		String saddress;
		int cid;
		int sid;
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;
		SimpleDateFormat sdf = null;
		Student stu = null;
		// 添加学生成功变量
		int i = 0;

		switch (num) {
		case 1:
			// 查询学生信息
			System.out.println("请输入添加的学生姓名:");
			sname = input.next();
			System.out.println("请输入添加的学生年龄:");
			sage = input.nextInt();
			System.out.println("请输入添加的学生性别:");
			ssex = input.next();
			System.out.println("请输入添加的学生生日(1990.01.01):");
			sdate = input.next();
			sdf = new SimpleDateFormat("yyyy.mm.dd");

			try {
				utilDate = sdf.parse(sdate);
				sqlDate = new java.sql.Date(utilDate.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("请输入添加的学生地址:");
			saddress = input.next();
			System.out.println("请输入添加的学生班级号:");
			cid = input.nextInt();

			stu = new Student();

			stu.setSname(sname);
			stu.setSage(sage);
			stu.setSsex(ssex);
			stu.setSbirthday(sqlDate);
			stu.setSaddress(saddress);
			stu.setCid(cid);

			i = service.add(stu);

			if (i != -1) {
				System.out.println("添加学生成功" + sname + "同学信息");
			} else {
				System.out.println("添加失败");
			}

			show();
			break;
		case 2:
			// 修改学生信息
			System.out.println("根据学生sid修改学生信息，请输入你需要修改的sid:");
			sid = input.nextInt();
			Student queryById = service.queryById(sid);
			System.out.println(queryById);

			System.out.println("请输入需要修改的学生姓名:");
			sname = input.next();
			System.out.println("请输入需要修改的学生年龄:");
			sage = input.nextInt();
			System.out.println("请输入需要修改的学生性别:");
			ssex = input.next();
			System.out.println("请输入需要修改的学生生日(1990.01.01):");
			sdate = input.next();
			sdf = new SimpleDateFormat("yyyy.mm.dd");
			try {
				utilDate = sdf.parse(sdate);
				sqlDate = new java.sql.Date(utilDate.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("请输入需要修改的学生地址:");
			saddress = input.next();
			System.out.println("请输入需要修改的学生班级号:");
			cid = input.nextInt();

			// 进行学生的信息修改
			stu = new Student();
			stu.setSname(sname);
			stu.setSage(sage);
			stu.setSsex(ssex);
			stu.setSbirthday(sqlDate);
			stu.setSaddress(saddress);
			stu.setCid(cid);

			i = service.add(stu);

			if (i != -1) {
				System.out.println("根据学生的id修改学生信息:" + sid + "学生信息");
			} else {
				System.out.println("修改学生信息失败");
			}
			show();

			break;

		case 3:
			// 删除学生
			System.out.println("根据sid删除一个学生信息，请输入你要删除的sid:");
			sid = input.nextInt();
			int delete = service.deleteById(sid);
			System.out.println("你输入的sid为:" + sid);
			System.out.println("你所删除的学生信息为" + delete);

			show();
			break;
		case 4:
			// 根据学生sid查询单个学生信息
			System.out.println("根据学生sid查询单个学生信息，请输入你的sid:");
			sid = input.nextInt();
			Student byId = service.queryById(sid);
			System.out.println("你输入的sid为:" + sid);
			System.out.println("查询到以下学生信息" + "\n" + byId);

			break;

		case 5:
			// 根据list进行查询所有学生信息
			List<Student> list = service.queryAll();
			// 遍历查询
			for (Student s : list) {
				System.out.println(s);
			}

			show();
			break;
		case 6:
			System.out.println("欢迎下次到来!");
			break;
		default:
			break;
		}
	}
}