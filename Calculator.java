class InvalidPostfixException extends Exception{
	public InvalidPostfixException(String s){
		super(s);
	}
}
class InvalidExprException extends Exception{
	public InvalidExprException(String s){
		super(s);
	}
}

class Calculator{
	
	public int evaluatePostFix(String s) throws InvalidPostfixException{
		MyStack mm=new MyStack();
		String sarr[]=s.trim().split("\\s+");
		int signs=0;
		int ints=0;
		for(int i=sarr.length-1;i>=0;i--) {
			if(sarr[i].equals("*")||sarr[i].equals("-")|| sarr[i].equals("+")) {
				mm.push(sarr[i]);
				signs++;
			}
			else {
				try {
					Integer k=Integer.parseInt(sarr[i]);
					if(k<0) {
						int jj=5;
						int kk=0;
						int ll=jj/kk;
					}
					ints++;
					mm.push(k);
				}
				catch(Exception e) {
					throw new InvalidPostfixException(sarr[i]+" is not allowed");
				}
			}
		}
		
		if(signs+1!=ints) {
			throw new InvalidPostfixException("invalid");
		}
		else {
			try {
				while(signs>0) {
				int i=0;
				Object ar1[]=new Object[mm.size()];
				while(!mm.top().equals("*")&&!mm.top().equals("-")&& !mm.top().equals("+")) {
					ar1[i]=mm.pop();
					i++;
				}
				if(mm.top().equals("*")) {
					Object k=(int)ar1[i-2]*(int)ar1[i-1];
					mm.pop();
					mm.push(k);
				}
				else if(mm.top().equals("-")) {
					Object k=(int)ar1[i-2]-(int)ar1[i-1];
					mm.pop();
					mm.push(k);
				}
				else if(mm.top().equals("+")) {
					Object k=(int)ar1[i-2]+(int)ar1[i-1];
					mm.pop();
					mm.push(k);
				}
				for(int z=i-3;z>=0;z--) {
					mm.push(ar1[z]);
				}
				signs--;
				}
				return (int)mm.top();
			}catch(Exception e) {
				throw new InvalidPostfixException("InvalidExpression");
			}
			
		}
	}
	public int os(Object s) {
		if(s.equals("+")||s.equals("-")) {
			return 1;
		}
		else if(s.equals("*")) {
			return 2;
		}
		else {
			return 0;
		}
	}
	public String convertExpression(String s) throws InvalidExprException {
		String dups="";
		MyStack kk=new MyStack();
		for(int i=0;i<s.length();) {
			int m=i+1;
			if(Character.isDigit(s.charAt(i))) {
			while(m<s.length()) {
				if(Character.isDigit(s.charAt(m))) {
					m++;
				}
				else {
					break;
				}
			}}
			else if(s.charAt(i)==' ') {
				i++;
				continue;
			}
			
			if(dups.length()==0) {
				dups=s.substring(i, m);
			}
			else {
				dups=dups+" "+s.substring(i, m);
			}
			i=m;
		}
		String ar[]=dups.split(" ");
		int par=0;
		int op=0;
		int ints=0;
		String z="";
		for(int i=0;i<ar.length;i++) {
			if(ar[i].equals(")")) {
				par--;
				if(kk.isEmpty()){
					throw new InvalidExprException("InvalidExpression");
				}
				try{
				while(kk.top()!="(") {
					z=z+" "+kk.pop();
				}
				kk.pop();
			}catch(Exception e){
				throw new InvalidExprException("InvalidExpression");
			}
			}
			else if(ar[i].equals("(")) {
				par++;
				kk.push("(");
			}
			else if(ar[i].equals("+")||ar[i].equals("-")||ar[i].equals("*")) {
				op++;
				try{
				while(!kk.isEmpty()&&kk.top()!="("&&os(ar[i])<=os(kk.top())) {
					z=z+" "+kk.pop();	
				}
				kk.push(ar[i]);
			}catch(Exception e){
				throw new InvalidExprException("InvalidExpression");
			}
			}
			else if(ar[i].matches("-?\\d+")) {
				ints++;
				z=z+" "+ar[i];
			}
			else {
				throw new InvalidExprException(ar[i]+" is not allowed");
			}
			if(op>ints) {
				throw new InvalidExprException("InvalidExpression");
			}
			if(op+2<=ints){
				throw new InvalidExprException("InvalidExpression");
			}
		}
		if (op+1!=ints) {
			throw new InvalidExprException("InvalidExpression");
		}
		if(par!=0){
			throw new InvalidExprException("InvalidExpression");
		}
		try{
		while(!kk.isEmpty()) {
			z=z+" "+kk.pop();
		}
		
	}catch(Exception e){
		throw new InvalidExprException("InvalidExpression");
	}
	String fins=z.trim();
	try{
		evaluatePostFix(fins);
	}catch(Exception e){
		throw new InvalidExprException("InvalidExpression");
	}
	return fins;
	}
}