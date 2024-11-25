package chapter04;

import java.util.Objects;

public class Rect {
	public final int w;
	public final int h;
	
	public Rect(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	@Override
	public String toString() {
		return "Point [w=" + w + ", h=" + h + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(w, h);
	}
	
	@Override
	public boolean equals(Object obj) {
	 	if (this == obj) {
	 		return true;
	 	}
	 	if (obj == null) {
	 		return false;
	 	}
	 	if (getClass() != obj.getClass()) {
	 		return false;
	 	}
	 	
	 	Rect other = (Rect)obj;
	 	return (h == other.h && w == other.w);
	}
}
