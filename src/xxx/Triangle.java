package xxx;

public class Triangle {
	public static void main(String[] args) {
		int layer = 5;//設定層數
		
		leftDown(layer);//直角三角形(左下)
		rightDown(layer);//直角三角形(右下)
		leftUp(layer);//直角三角形(左上)
		rightUp(layer);//直角三角形(左上)
		emptyTriangle(layer);//空心三角形
		isoscelesTriangle1(layer);//等腰三角形(第一種)
		isoscelesTriangle2(layer);//等腰三角形(第二種)
		invertedTriangle(layer);//倒三角形
		diamond(layer);//菱形
	}

	public static void leftDown(int layer) {
		for (int i = 0; i < layer; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void rightDown(int layer) {
		for (int i = 0; i < layer; i++) {
			for (int k = 0; k < layer - 1 - i; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void leftUp(int layer) {
		for (int i = 1; i <= layer; i++) {
			for (int j = 1; j <= layer; j++) {
				if (i <= j)
					System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void rightUp(int layer) {
		for (int i = 1; i <= layer; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= layer - i + 1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public static void emptyTriangle(int layer) {
		for (int i = 1; i <= layer; i++) {
			for (int k = 1; k <= layer - i; k++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= (2 * i - 1); j++) {
				if (i == 1 || i == layer)
					System.out.print("*");
				else {
					if (j == 1 || j == 2 * i - 1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
			}
			System.out.println();
		}
	}

	public static void isoscelesTriangle1(int layer) {
		int i, j;
		for (i = 1; i <= layer; i++) {
			for (j = 1; j <= layer - i; j++) {
				System.out.print(" ");
			}
			for (j = 1; j <= i; j++) {
				System.out.print("1");
			}
			for (j = 1; j <= i - 1; j++) {
				System.out.print("2");
			}
			System.out.println();
		}
	}

	public static void isoscelesTriangle2(int layer) {
		for (int i = 0; i < layer; i++) {
			for (int j = 0; j < layer - 1 - i; j++) {
				System.out.print(" ");
			}
			for (int k = 0; k < 2 * i + 1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void invertedTriangle(int layer) {
		for(int i=1;i<=layer;i++) {
			for(int j=1;j<i;j++) {
				System.out.print(" ");
			}
			for(int k=1;k<=((layer-i)*2)+1;k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void diamond(int layer) {
	    for(int i = 1; i <= layer; i++){  
	        for(int j = layer-i; j >= 0;j--) {
	        	 System.out.print(" ");  
	        }
	           
	        for(int k = 1; k <= (i*2)-1; k++){
	        	System.out.print("*");
	        }
	              
	        System.out.println("");
	    }  
	    for(int i = layer-1; i >= 1; i--){
	        for(int j = layer-i; j >= 0; j--) { 
	        	System.out.print(" ");  
	        }
	        for(int k = 1; k <= 2*i-1; k++) {
	        	 System.out.print("*");  
	        }
	        System.out.println("");  
	    } 
	}
	
}
