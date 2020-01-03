package pharmacy.management.bean;

public class LogBean {
	private int id;
	private String dateTime;
	private String content;
	private String name;

	public LogBean(int id, String dateTime, String content, String name) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.content = content;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
