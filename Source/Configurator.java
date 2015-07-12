/*
 * 
 */
public class Configurator implements Configuration {
	
	private int size;
	private int datatype;
	private int compressiononoff;
	
	public Configurator (int size, int datatype, int compressiononoff)
	{
		this.size=size;
		this.datatype=datatype;
		this.compressiononoff=compressiononoff;
	} 

	@Override
	public int getSize() {
		return size;
		
	}

	@Override
	public int getDataType() {	
		return datatype;
	
	}

	@Override
	public int getCompressionMode() {
		return compressiononoff;
	}

}
