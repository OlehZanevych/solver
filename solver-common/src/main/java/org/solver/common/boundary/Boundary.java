package org.solver.common.boundary;

import org.solver.common.condition.Condition;
import org.solver.common.point2d.Point2D;

public class Boundary {
	
	protected Point2D<Integer> p1;
	protected Point2D<Integer> p2;
	protected Condition condition;
	protected double b;
	protected double d;
	protected double c;
	
	public Boundary() {
		
	}
	
	public Boundary(Point2D<Integer> p1, Point2D<Integer> p2, Condition condition, double c) {
		this.p1 = p1;
		this.p2 = p2;
		this.c = c;
		this.condition = condition;
	}
	
	public int max(int a, int b) {
		return a >= b ? a : b;
	}
	
	public int min(int a, int b) {
		return a <= b ? a : b;
	}

	public Point2D<Integer> getP1() {
		return p1;
	}

	public void setP1(Point2D<Integer> p1) {
		this.p1 = p1;
	}

	public Point2D<Integer> getP2() {
		return p2;
	}

	public void setP2(Point2D<Integer> p2) {
		this.p2 = p2;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public int getXMax() {
		return max(p1.getX(), p2.getX());
	}

	public int getXMin() {
		return min(p1.getX(), p2.getX());
	}

	public int getYMax() {
		return max(p1.getY(), p2.getY());
	}

	public int getYMin() {
		return min(p1.getY(), p2.getY());
	}
	
}
