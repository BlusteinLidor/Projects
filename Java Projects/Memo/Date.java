import java.io.Serializable;
import java.util.Objects;

/**
 * Maman14EX2
 * @author Lidor Blustein
 * id 314993460
 *
 */

public class Date implements Serializable{
	private int day, month, year;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Date)) {
			return false;
		}
		Date other = (Date) obj;
		if(this.day == other.day && this.month == other.month
				&& this.year == other.year) {
			return true;
		}
		return false;
	}
	
	
	
}
