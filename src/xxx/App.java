package xxx;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

import xxx.TimeClockEntity;

/**
�W�����D:�p��H�Ʀ���

�D��:
	�p��C����u������`���I�`�~��,�ýШϥ�printSomebodyPay�N���G��ܦbconsole�W

�ӳ��y�z:
	��l��ƴ��ѨC����u�C�骺���d�����P�����~,�ݭn�p��X������I�`�~��(�t�[�Z�O)�P������I�`�~��
	�ھڬ���Ұ�k�W�w,�u�ɶW�L8�p�ɫ�,�[�Z�O���̤U�C���v�p��
	��0~8�p�ɵ����򥻮��~
	��8~12�p�ɵ����򥻮��~x1.5��
	��12�p�ɶ}�l�����򥻮��~x2��
	
���D����:
	1.5������d��ƪ����o�覡���I�sTimeClockService.getData();
	2.���F�����D���������ѥ~,�A�i�H��o��{���������޿�(�tprintSomebodyPay)���ק�
	3.���{���ҨϥΪ�����ɶ�����LocalDateTime,�o�O�@�ө�jdk8��~�ޤJ��class
	LocalDateTime���@�M������Api�i�H�p���Ӯɶ������Z,�Цۦ�j��
	
�Y�n���յ{���O�_�i�H�B�@,�i�H��������main��k

@author Rhys
*/


public class App {
	
	public static void main(String[] args) {
		//�����Ҧ����d���
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
	 * �榡�Ʃ�console�L�X�Y�H���~��
	 * @param name �m�W
	 * @param pay �~��
	 */
	private static void printSomebodyPay(String name, double pay) {
		System.out.printf("%s: %.2f$\n", name, pay);
	}

}
