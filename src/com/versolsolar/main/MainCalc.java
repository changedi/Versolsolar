/**
 * 
 */
package com.versolsolar.main;

/**
 * @author Administrator
 * 
 */
public class MainCalc {

	public static final double f = 235;// 屈服强度，单位：MPa=1,000,000Pa(===N/mm^2)

	public static final double PI = Math.PI;// 圆周率
	
	public static final double E = 206*1000;// 弹性模量，单位：N/mm^2 

	double An;// 圆形的环形横截面积，单位：mm^2

	public double getAn() {
		return An;
	}

	public void setAn(double an) {
		An = an;
	}

	public double getSigma() {
		return sigma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	public double getStabability() {
		return stabability;
	}

	public void setStabability(double stabability) {
		this.stabability = stabability;
	}

	public double getFai() {
		return fai;
	}

	public void setFai(double fai) {
		this.fai = fai;
	}

	double sigma;// 截面应力，单位：N
	
	double stabability;// 稳定性，单位：N/mm^2
	
	double fai;// 稳定系数
	
	double L0;//计算长度

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainCalc calc = new MainCalc();
		//calc.calcSigma(t, R, N);
		
		//System.out.println(calc.calc(1, 1, 1));

	}

	/**
	 * @param t	圆管厚度
	 * @param R	圆管外半径
	 * @param N	轴心拉力或轴心压力
	 * @return 应力sigma
	 */
	public double calcSigma(double t, double R, double N) {
		An = PI * t * (2 * R - t);
		sigma = An / N;
		return sigma;
	}
	
	/**
	 * 
	 * @param sigma 截面应力
	 * @return 应力sigma及是否满足
	 */
	public boolean isSigmaSat() {
		if (sigma <= f) {
			return true;// 强度满足
		} else {
			return false;// 强度不满足
		}
	}

	private double sqrt(double x) {
		return Math.sqrt(x);
	}
	
	private double pow2(double x) {
		return x*x;
	}
	
	/**
	 * 计算稳定性
	 * @param N	轴心拉力或轴心压力
	 * @param A	圆形的环形横截面积
	 * @return
	 */
	public boolean isStable(double N) {
		stabability = N / (fai*An);
		if(stabability<=f) {
			return true;//稳定性满足
		}
		else {
			return false;//稳定性不满足
		}
	}
	
	/**
	 * 计算稳定系数
	 * @param Ix	x向惯性矩
	 * @param Iy	y向惯性矩
	 */
	public void calcFai(double Ix, double Iy) {
		double alpha1 = 0.65;   //alpha值目前仅针对b类情况
		double alpha2 = 0.965;
		double alpha3 = 0.300;
		double ix = sqrt(Ix/An);//回转半径
		double iy = sqrt(Iy/An);//
		double lamdax = L0/ix;//x向长细比
		double lamday = L0/iy;//y向长细比
		double lamda1 = lamdax/PI * sqrt(f/E);
		double fai1,fai2;//稳定系数
		if(lamda1<=0.215) {
			fai1 = 1 - alpha1*pow2(lamda1);
		}
		else {
			double tmp = alpha2+alpha3*lamda1+pow2(lamda1);
			fai1 = 1/(2*pow2(lamda1)) * (tmp-sqrt(pow2(tmp)-4*pow2(lamda1)));
		}
		double lamda2 = lamday/PI * sqrt(f/E);
		if(lamda2<=0.215) {
			fai2 = 1 - alpha1*pow2(lamda2);
		}
		else {
			double tmp = alpha2+alpha3*lamda2+pow2(lamda2);
			fai2 = 1/(2*pow2(lamda2)) * (tmp-sqrt(pow2(tmp)-4*pow2(lamda2)));
		}
		fai = Math.min(fai1, fai2);
	}

	/**
	 * 
	 * @param type 
	 * @param L	实际长度
	 */
	public void calcL0(int type, double L) {
		switch (type) {
		case 1:
			L0 = L * 0.5;
			break;
		case 2:
			L0 = L * 0.7;
			break;
		case 3:
			L0 = L * 1.0;
			break;
		case 4:
			L0 = L * 1.0;
			break;
		case 5:
			L0 = L * 2.0;
			break;
		case 6:
			L0 = L * 2.0;
			break;
		default:
			L0 = L * 1.0;
		}
	}
}
