package xxx;

public class MidString {
	public static void main(String[] args) {
		String test1 = "test";
		String test2 = "testing";
		String test3 = "A";
		GetMiddleCharacters(test1);
		GetMiddleCharacters(test2);
		GetMiddleCharacters(test3);
		
	}
	
	
	public static String GetMiddleCharacters(String inputStr) {
        int i=inputStr.length()/2;
        int length = inputStr.length();
        if(length%2==1){
            String s = inputStr.substring(i,i+1);
            System.out.println(s);
            return s;
            
        }else{
            String s = inputStr.substring(i-1,i+1);
            System.out.println(s);
            return s;
            
        }
    }
	
//	select e.Id,e.name,s.name as surname from Employee e 
//
//	inner join Surname s on e.Id=s.Id;
}
