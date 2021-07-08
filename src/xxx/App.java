package xxx;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

import xxx.TimeClockEntity;

/**
上機考題:計算人事成本

題目:
	計算每位員工的當月總應付總薪資,並請使用printSomebodyPay將結果顯示在console上

細部描述:
	原始資料提供每月員工每日的打卡紀錄與當日時薪,需要計算出當日應付總薪資(含加班費)與整月應付總薪資
	根據美國勞基法規定,工時超過8小時後,加班費應依下列倍率計算
	第0~8小時給予基本時薪
	第8~12小時給予基本時薪x1.5倍
	第12小時開始給予基本時薪x2倍
	
答題提示:
	1.5月份打卡資料的取得方式為呼叫TimeClockService.getData();
	2.除了本試題說明的註解外,你可以對這支程式的任何邏輯(含printSomebodyPay)做修改
	3.本程式所使用的日期時間物件為LocalDateTime,這是一個於jdk8後才引入的class
	LocalDateTime有一套相關的Api可以計算兩個時間的間距,請自行搜索
	
若要測試程式是否可以運作,可以直接執行main方法

@author Rhys
*/


public class App {
	
	public static void main(String[] args) {
		//取的所有打卡資料
		final List<TimeClockEntity> timeClockDatas = TimeClockService.getData();
		//TODO
		List<Long> worktime = new ArrayList<Long>();
		Map<String, Double>empnameAndPay = new TreeMap<String, Double>();
		
		for(int i=0; i<timeClockDatas.size(); i++) {
			LocalDateTime startWorkTime = timeClockDatas.get(i).getTimeIn();
			LocalDateTime offWorkTime = timeClockDatas.get(i).getTimeOut();
			Duration hours =  Duration.between(startWorkTime, offWorkTime);
			long workingHours = hours.toHours();
			
			double totalHours=0;
			
			if(workingHours<=8) {
				totalHours=totalHours+workingHours;
			}else if(workingHours <= 12) {
				totalHours=8+((workingHours-8)*1.5);
			}else if(workingHours >= 12) {
				totalHours=8+(4*1.5)+((workingHours-12)*2);
			}
			
			String name = timeClockDatas.get(i).getEmployeeName();
			double pay = totalHours*timeClockDatas.get(i).getHourilyRate();
			
			pay=empnameAndPay.getOrDefault(name, 0.0)+pay;
			empnameAndPay.put(name, pay);
		}
		
		Set<Entry<String, Double>> e = empnameAndPay.entrySet();
		for (Entry<String, Double> entry : e) {
			System.out.println(entry);
		}
		
		
	}
	
	/**
	 * 格式化於console印出某人的薪資
	 * @param name 姓名
	 * @param pay 薪資
	 */
	private static void printSomebodyPay(String name, double pay) {
		System.out.printf("%s: %.2f$\n", name, pay);
	}

}
