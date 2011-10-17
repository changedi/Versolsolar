/**
 * 
 */
package com.versolsolar.main;

/**
 * @author Administrator
 * 
 */
public class MainCalc {

	public static final double f = 235;// ����ǿ�ȣ���λ��MPa=1,000,000Pa(===N/mm^2)

	public static final double PI = Math.PI;// Բ����
	
	public static final double E = 206*1000;// ����ģ������λ��N/mm^2 

	double An;// Բ�εĻ��κ���������λ��mm^2

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

	double sigma;// ����Ӧ������λ��N
	
	double stabability;// �ȶ��ԣ���λ��N/mm^2
	
	double fai;// �ȶ�ϵ��
	
	double L0;//���㳤��

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
	 * @param t	Բ�ܺ��
	 * @param R	Բ����뾶
	 * @param N	��������������ѹ��
	 * @return Ӧ��sigma
	 */
	public double calcSigma(double t, double R, double N) {
		An = PI * t * (2 * R - t);
		sigma = An / N;
		return sigma;
	}
	
	/**
	 * 
	 * @param sigma ����Ӧ��
	 * @return Ӧ��sigma���Ƿ�����
	 */
	public boolean isSigmaSat() {
		if (sigma <= f) {
			return true;// ǿ������
		} else {
			return false;// ǿ�Ȳ�����
		}
	}

	private double sqrt(double x) {
		return Math.sqrt(x);
	}
	
	private double pow2(double x) {
		return x*x;
	}
	
	/**
	 * �����ȶ���
	 * @param N	��������������ѹ��
	 * @param A	Բ�εĻ��κ�����
	 * @return
	 */
	public boolean isStable(double N) {
		stabability = N / (fai*An);
		if(stabability<=f) {
			return true;//�ȶ�������
		}
		else {
			return false;//�ȶ��Բ�����
		}
	}
	
	/**
	 * �����ȶ�ϵ��
	 * @param Ix	x����Ծ�
	 * @param Iy	y����Ծ�
	 */
	public void calcFai(double Ix, double Iy) {
		double alpha1 = 0.65;   //alphaֵĿǰ�����b�����
		double alpha2 = 0.965;
		double alpha3 = 0.300;
		double ix = sqrt(Ix/An);//��ת�뾶
		double iy = sqrt(Iy/An);//
		double lamdax = L0/ix;//x��ϸ��
		double lamday = L0/iy;//y��ϸ��
		double lamda1 = lamdax/PI * sqrt(f/E);
		double fai1,fai2;//�ȶ�ϵ��
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
	 * @param L	ʵ�ʳ���
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
