package immoc.bean;
import java.util.List;

public class Command {
       String id;
       String name;
       String description;
       List<Command_Content> contentList;
       
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
       
     public List<Command_Content> getContentList(){
    	 return contentList;
     }
     
     
}
