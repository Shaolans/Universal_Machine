package sum.interfaces.iast;

import java.util.List;

public interface IASTprogram extends IAST {
	public List<IASTstatement> getProgram();
}
