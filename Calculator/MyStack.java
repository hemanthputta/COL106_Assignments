class EmptyStackException extends Exception{
	public EmptyStackException(String s){
		super(s);
	}
}
public class MyStack implements StackInterface{
	private Object arr[]={123456789};
	private int i=0;
	int siz=0;
	public void push(Object o){
		if(i==0){
			arr[0]=o;
			i++;
			siz++;
		}
		else if(siz==0) {
			arr=new Object[1];
			arr[0]=o;
			i++;
			siz++;
			
		}
		else {
			if(siz==arr.length) {
				Object ex1[]=new Object[2*arr.length];
				for(int m=0;m<ex1.length;m++) {
					if(m<=arr.length-1) {
						ex1[m]=arr[m];
					}
					else if(m==arr.length){
						ex1[m]=o;
					}
					else {
						ex1[m]=null;
					}
				}
				arr=ex1;
				i++;
				siz++;
			}
			else {
				arr[siz]=o;
				siz++;
			}
		}
	}
	
	public int size() {
		return siz;
	}
	public Object pop() throws EmptyStackException{
		if(i==0||siz==0){
			throw new EmptyStackException("EmptyStackException");
		}
		else{
			try{
			Object k=arr[siz-1];
			i++;
			siz--;
			return k;
		}catch(Exception e){
			throw new EmptyStackException("EmptyStackException");
		}
		}
	}
	public Object top() throws EmptyStackException {
		if(i==0||siz==0){
			throw new EmptyStackException("EmptyStackException");
		}
		else{
			try{
			Object a=arr[siz-1];
			return a;
		}catch(Exception e){
			throw new EmptyStackException("EmptyStackException");
		}
		}
	}
	public boolean isEmpty() {
		if(i==0 || siz==0) {
			return true;
		}
		else {
			return false;
		}
	}
	private String s;
	public String toString() {
		String arr1[]=new String[siz];
		for(int i=0;i<siz;i++) {
			arr1[siz-i-1]=""+arr[i];
		}
		s=String.join(", ", arr1);
		s="["+s+"]";
		return s;
	}
}