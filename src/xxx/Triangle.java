package xxx;

public class Triangle {
	public static void main(String[] args) {
		int layer = 5;//�]�w�h��
		
		leftDown(layer);//�����T����(���U)
		rightDown(layer);//�����T����(�k�U)
		leftUp(layer);//�����T����(���W)
		rightUp(layer);//�����T����(���W)
		emptyTriangle(layer);//�ŤߤT����
		isoscelesTriangle1(layer);//���y�T����(�Ĥ@��)
		isoscelesTriangle2(layer);//���y�T����(�ĤG��)
		invertedTriangle(layer);//�ˤT����
		diamond(layer);//�٧�
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
