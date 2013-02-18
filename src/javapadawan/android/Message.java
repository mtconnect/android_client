package javapadawan.android;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message>{
	static SimpleDateFormat FORMATTER = 
		new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	private String title;
	private URL link;
	private String description;
	private Date date;
	private String estop;
	private String alarm;
	private String power;
	private String pathf;
	private String pathp;
	private String block;
	private String control;
	private String line;
	private String program;
	private String execution;
	private String xpos;
	private String ypos;
	private String zpos;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}
	public String getEstop() {
		return estop;
	}

	public void setEstop(String estop) {
		this.estop = estop.trim();
	}
	
	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm.trim();
	}
	
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power.trim();
	}
	
	public String getPathf() {
		return pathf;
	}

	public void setPathf(String pathf) {
		this.pathf = pathf.trim();
	}
	
	public String getPathp() {
		return pathp;
	}

	public void setPathp(String pathp) {
		this.pathp = pathp.trim();
	}
	
	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block.trim();
	}
	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control.trim();
	}
	
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line.trim();
	}
	
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program.trim();
	}
	
	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution.trim();
	}
	
	public String getXpos() {
		return xpos;
	}

	public void setXpos(String xpos) {
		this.xpos = xpos.trim();
	}
	
	public String getYpos() {
		return ypos;
	}

	public void setYpos(String ypos) {
		this.ypos = ypos.trim();
	}
	
	public String getZpos() {
		return zpos;
	}

	public void setZpos(String zpos) {
		this.zpos = zpos.trim();
	}
	
	// getters and setters omitted for brevity 
	public URL getLink() {
		return link;
	}
	
	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getDate() {
		return FORMATTER.format(this.date);
	}

	public void setDate(String date) {
		// pad the date if necessary
		while (!date.endsWith("00")){
			date += "0";
		}
		try {
			this.date = FORMATTER.parse(date.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Message copy(){
		Message copy = new Message();
		copy.title = title;
		copy.link = link;
		copy.description = description;
		copy.date = date;
		return copy;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title: ");
		sb.append(title);
		sb.append('\n');
		sb.append("Date: ");
		sb.append(this.getDate());
		sb.append('\n');
		sb.append("Link: ");
		sb.append(link);
		sb.append('\n');
		sb.append("Description: ");
		sb.append(description);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int compareTo(Message another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.date.compareTo(date);
	}
}
