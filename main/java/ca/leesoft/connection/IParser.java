package ca.leesoft.connection;

public interface IParser<T> {
	T parse(String content);
	String hasMore();
	void setContent(String content);
}
