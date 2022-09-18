import java.util.EmptyStackException;

public interface StackInterface{
	public void push(Object o);
	public Object pop() throws Exception;
	public Object top() throws Exception;
	public boolean isEmpty();
	public String toString();
}