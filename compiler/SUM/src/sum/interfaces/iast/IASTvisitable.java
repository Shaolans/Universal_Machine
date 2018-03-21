package sum.interfaces.iast;

public interface IASTvisitable {
	public void accept(IASTvisitor visitor, int context);
}
