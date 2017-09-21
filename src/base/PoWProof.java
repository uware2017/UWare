package base;

import java.math.BigInteger;
import java.util.List;

public class PoWProof {

	private BigInteger fCT;
	
	private List<BigInteger> bCTs;
	
	private List<BlockProperty> bCTs2;
	
	public PoWProof(BigInteger fCT, List<BigInteger> bCTs) {
		
		this.fCT = fCT;
		this.bCTs = bCTs;
	}
	
	public PoWProof(List<BlockProperty> bCTs2) {
		
		this.bCTs2 = bCTs2;
	}

	public BigInteger getfCT() {
		return fCT;
	}

	public List<BigInteger> getbCTs() {
		return bCTs;
	}
	
	public List<BlockProperty> getbCTs2() {
		return bCTs2;
	}
}
