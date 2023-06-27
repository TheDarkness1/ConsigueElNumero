package application;

import java.util.Random;

public class Juego {

	private Random random = new Random();
	
	private int numeroEjercicio = random.nextInt(1,100);
	private boolean relacionOrden = random.nextBoolean();
	private int operacionM = random.nextInt(1,5);
	
	private int[] numerosCondicion;
	
	public int getNumeroEjercicio () {
		return numeroEjercicio;
	}
	
	public boolean getRelacionOrden() {
		return relacionOrden;
	}
	
	public int getOperacion() {
		return operacionM;
	}
	
	public int[] getNumerosCondicion(int numE, int opeM, boolean relaO) {
		
		numerosCondicion = new int[3];
		numerosCondicion[0] = random.nextInt(0,101);
		int num1 = numerosCondicion[0];
		int num2 = 0;
		int num3 = 0;
		int operacion;
		
		if(opeM == 1) {
			if (relaO) {
				num2 = random.nextInt(0,101);
				operacion = num1+num2;
				if(operacion>numE) {
					num3 = random.nextInt(0,101);
				}else { 
					if(operacion>1){
						num3 = random.nextInt(Math.ceilDiv(numE,num1+num2)+1,101);
					}else {
						num3 = random.nextInt(numE-num1-num2+1,101);
					}
				}
			}else {
				if(numE<3){
					if(100*numE-num1<100) {
						num2 = random.nextInt(0,101);
					}else {
						num2 = random.nextInt(0,100*numE-num1);
					}
				}else {
					num2 = random.nextInt(0,101);
				}
				operacion = num1+num2;
				if(operacion<numE) {
					num3=random.nextInt(0,101);
				}else {
					num3=random.nextInt(Math.ceilDiv(operacion,numE)+1,101);
				}
			}
		}
		
		if(opeM == 2) {
			if (relaO) {
				num2 = random.nextInt(0,101+num1-numE);
				operacion = num1-num2;
				if(operacion>numE) {
					num3 = random.nextInt(0,101);
				}else {
					num3 = random.nextInt(numE-num1+num2+1,101);
				}
			}else {
				num2 = random.nextInt(0,101);
				operacion = num1-num2;
				if(operacion<numE) {
					num3 = random.nextInt(0,101);
				}else {
					num3 = random.nextInt(Math.ceilDiv(operacion,numE)+1,101);
				}
			}
		}		
		
		if(opeM == 3) {
			if (relaO) {
				num2 = random.nextInt(0,101);
				operacion = num1*num2;
				if(operacion>numE) {
					num3 = random.nextInt(0,101);
				}else {
					if(operacion>1) {
						num3 = random.nextInt(Math.ceilDiv(numE,operacion)+1,101);
					}else {
						num3 = random.nextInt(numE-operacion+1,101);
					}
				}
			}else {
				if(num1==0) {
					num2 = random.nextInt(0,101);
				}else {
					if(Math.ceilDiv(100*numE,num1)>100) {
						num2 = random.nextInt(0,101);
					}else {
						num2 = random.nextInt(0,Math.floorDiv(100*numE,num1));
					}
				}
				operacion = num1*num2;
				if(operacion<numE) {
					num3 = random.nextInt(0,101);
				}else {
					num3 = random.nextInt(Math.ceilDiv(operacion,numE)+1,101);
				}
			}
		}
		
		
		if(opeM == 4) {
			if (relaO) {
				num2 = random.nextInt(1,101);
				float op = num1/num2;
				if(op>numE) {
					num3 = random.nextInt(0,101);
				}else {
					if(op>1) {
						num3 = random.nextInt(Math.ceilDiv(numE*num2,num1)+1,101);
					}else {
						num3 = random.nextInt(numE-Math.ceilDiv(num1,num2)+1,101);
					}
				}
			}else {
				num2 = random.nextInt(1,101);
				float op = num1/num2;
				if(op<numE) {
					num3 = random.nextInt(0,101);
				}else {
					num3 = random.nextInt(Math.ceilDiv(num1,num2*numE)+1,101);
				}
			}
		}
		
		numerosCondicion[1] = num2;
		numerosCondicion[2] = num3;
		
		return numerosCondicion;
	}
	
	public Object[] verificarRespuesta(int operacion, int[] numerosCondicion, int numE, boolean relacionO, String opSeleccionada) {
		
		boolean esCorrecta = false;
		boolean esMasCercana = false;
		double num1 = numerosCondicion[0];
		double num2 = numerosCondicion[1];
		double num3 = numerosCondicion[2];
		double numR = 0;
		String operacionR = "";
		String num1st = "";
		String num2st = "";
		String num3st = "";
		
		if(relacionO) {
			
			if(operacion==1) {
				
				if(opSeleccionada=="+") {
					double dif = ((num1+num2)+num3)-numE;
					double dif1 = ((num1+num2)-num3)-numE;
					double dif2 = ((num1+num2)*num3)-numE;
					double dif3 = ((num1+num2)/num3)-numE;
					if(num1+num2+num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"÷"+num3st;
					}
				}
				
				
				if(opSeleccionada=="-") {
					double dif = ((num1+num2)-num3)-numE;
					double dif1 = ((num1+num2)+num3)-numE;
					double dif2 = ((num1+num2)*num3)-numE;
					double dif3 = ((num1+num2)/num3)-numE;
					if(num1+num2-num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"÷"+num3st;
					}
				}
				
				
				
				if(opSeleccionada=="x") {
					double dif = ((num1+num2)*num3)-numE;
					double dif1 = ((num1+num2)-num3)-numE;
					double dif2 = ((num1+num2)+num3)-numE;
					double dif3 = ((num1+num2)/num3)-numE;
					if((num1+num2)*num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"÷"+num3st;
					}
				}
				
				
				
				if(opSeleccionada=="÷") {
					double dif = ((num1+num2)/num3)-numE;
					double dif1 = ((num1+num2)+num3)-numE;
					double dif2 = ((num1+num2)-num3)-numE;
					double dif3 = ((num1+num2)*num3)-numE;
					if((num1+num2)/num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"x"+num3st;
					}
				}
			}
			
			
			if(operacion==2) {
				
				if(opSeleccionada=="+") {
					double dif = ((num1-num2)+num3)-numE;
					double dif1 = ((num1-num2)-num3)-numE;
					double dif2 = ((num1-num2)*num3)-numE;
					double dif3 = ((num1-num2)/num3)-numE;
					if((num1-num2)+num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="-") {
					double dif = ((num1-num2)-num3)-numE;
					double dif1 = ((num1-num2)+num3)-numE;
					double dif2 = ((num1-num2)*num3)-numE;
					double dif3 = ((num1-num2)/num3)-numE;
					if((num1-num2)-num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="x") {
					double dif = ((num1-num2)*num3)-numE;
					double dif1 = ((num1-num2)-num3)-numE;
					double dif2 = ((num1-num2)+num3)-numE;
					double dif3 = ((num1-num2)/num3)-numE;
					if((num1-num2)*num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = ((num1-num2)/num3)-numE;
					double dif1 = ((num1-num2)+num3)-numE;
					double dif2 = ((num1-num2)-num3)-numE;
					double dif3 = ((num1-num2)*num3)-numE;
					if((num1-num2)/num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"x"+num3st;
					}
				}
			}
			
			
			if(operacion==3) {
				if(opSeleccionada=="+") {
					double dif = ((num1*num2)+num3)-numE;
					double dif1 = ((num1*num2)-num3)-numE;
					double dif2 = ((num1*num2)*num3)-numE;
					double dif3 = ((num1*num2)/num3)-numE;
					if((num1*num2)+num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"÷"+num3st;
					}
				}
				
				
				if(opSeleccionada=="-") {
				double dif = ((num1*num2)-num3)-numE;
				double dif1 = ((num1*num2)+num3)-numE;
				double dif2 = ((num1*num2)*num3)-numE;
				double dif3 = ((num1*num2)/num3)-numE;
					if((num1*num2)-num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="x") {
					double dif = ((num1*num2)*num3)-numE;
					double dif1 = ((num1*num2)-num3)-numE;
					double dif2 = ((num1*num2)+num3)-numE;
					double dif3 = ((num1*num2)/num3)-numE;
					if((num1*num2)*num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = ((num1*num2)/num3)-numE;
					double dif1 = ((num1*num2)+num3)-numE;
					double dif2 = ((num1*num2)-num3)-numE;
					double dif3 = ((num1*num2)*num3)-numE;
					if((num1*num2)/num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"x"+num3st;
					}
				}
			}
			
			
			if(operacion==4) {
				
				if(opSeleccionada=="+") {
					double dif = ((num1/num2)+num3)-numE;
					double dif1 = ((num1/num2)-num3)-numE;
					double dif2 = ((num1/num2)*num3)-numE;
					double dif3 = ((num1/num2)/num3)-numE;
					if((num1/num2)+num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="-") {
					double dif = ((num1/num2)-num3)-numE;
					double dif1 = ((num1/num2)+num3)-numE;
					double dif2 = ((num1/num2)*num3)-numE;
					double dif3 = ((num1/num2)/num3)-numE;
					if((num1/num2)-num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"÷"+num3st;
					}
				}
				
				
				
				if(opSeleccionada=="x") {
					double dif = ((num1/num2)*num3)-numE;
					double dif1 = ((num1/num2)-num3)-numE;
					double dif2 = ((num1/num2)+num3)-numE;
					double dif3 = ((num1/num2)/num3)-numE;
					if((num1/num2)*num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = ((num1/num2)/num3)-numE;
					double dif1 = ((num1/num2)+num3)-numE;
					double dif2 = ((num1/num2)-num3)-numE;
					double dif3 = ((num1/num2)*num3)-numE;
					if((num1/num2)/num3>numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"x"+num3st;
					}
				}
			}
			
			
		}else {
			
			
			if(operacion==1) {
				
				if(opSeleccionada=="+") {
					double dif = numE-((num1+num2)+num3);
					double dif1 = numE-((num1+num2)-num3);
					double dif2 = numE-((num1+num2)*num3);
					double dif3 = numE-((num1+num2)/num3);
					if(num1+num2+num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="-") {
					double dif = numE-((num1+num2)-num3);
					double dif1 = numE-((num1+num2)+num3);
					double dif2 = numE-((num1+num2)*num3);
					double dif3 = numE-((num1+num2)/num3);
					if(num1+num2-num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="x") {
					double dif = numE-((num1+num2)*num3);
					double dif1 = numE-((num1+num2)-num3);
					double dif2 = numE-((num1+num2)+num3);
					double dif3 = numE-((num1+num2)/num3);
					if((num1+num2)*num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = numE-((num1+num2)/num3);
					double dif1 = numE-((num1+num2)+num3);
					double dif2 = numE-((num1+num2)-num3);
					double dif3 = numE-((num1+num2)*num3);
					if((num1+num2)/num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1+num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1+num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1+num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"+"+num2st+")"+"x"+num3st;
					}
				}
			}
			
			
			if(operacion==2) {
				
				if(opSeleccionada=="+") {
					double dif = numE-((num1-num2)+num3);
					double dif1 = numE-((num1-num2)-num3);
					double dif2 = numE-((num1-num2)*num3);
					double dif3 = numE-((num1-num2)/num3);
					if((num1-num2)+num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="-") {
					double dif = numE-((num1-num2)-num3);
					double dif1 = numE-((num1-num2)+num3);
					double dif2 = numE-((num1-num2)*num3);
					double dif3 = numE-((num1-num2)/num3);
					if((num1-num2)-num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num1);
						num3st = String.format("%.0f",num2);
						operacionR = "("+num1st+"-"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="x") {
					double dif = numE-((num1-num2)*num3);
					double dif1 = numE-((num1-num2)-num3);
					double dif2 = numE-((num1-num2)+num3);
					double dif3 = numE-((num1-num2)/num3);
					if((num1-num2)*num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = numE-((num1-num2)/num3);
					double dif1 = numE-((num1-num2)+num3);
					double dif2 = numE-((num1-num2)-num3);
					double dif3 = numE-((num1-num2)*num3);
					if((num1-num2)/num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1-num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1-num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1-num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"-"+num2st+")"+"x"+num3st;
					}
				}	
			}
			
			
			if(operacion==3) {
				
				if(opSeleccionada=="+") {
					double dif = numE-((num1*num2)+num3);
					double dif1 = numE-((num1*num2)-num3);
					double dif2 = numE-((num1*num2)*num3);
					double dif3 = numE-((num1*num2)/num3);
					if((num1*num2)+num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="-") {
					double dif = numE-((num1*num2)-num3);
					double dif1 = numE-((num1*num2)+num3);
					double dif2 = numE-((num1*num2)*num3);
					double dif3 = numE-((num1*num2)/num3);
					if((num1*num2)-num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="x") {
					double dif = numE-((num1*num2)*num3);
					double dif1 = numE-((num1*num2)-num3);
					double dif2 = numE-((num1*num2)+num3);
					double dif3 = numE-((num1*num2)/num3);
					if((num1*num2)*num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = numE-((num1*num2)/num3);
					double dif1 = numE-((num1*num2)+num3);
					double dif2 = numE-((num1*num2)-num3);
					double dif3 = numE-((num1*num2)*num3);
					if((num1*num2)/num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1*num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1*num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1*num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"x"+num2st+")"+"x"+num3st;
					}
				}
			}
			
			
			if(operacion==4) {
				
				if(opSeleccionada=="+") {
					double dif = numE-((num1/num2)+num3);
					double dif1 = numE-((num1/num2)-num3);
					double dif2 = numE-((num1/num2)*num3);
					double dif3 = numE-((num1/num2)/num3);
					if((num1/num2)+num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="-") {
					double dif = numE-((num1/num2)-num3);
					double dif1 = numE-((num1/num2)+num3);
					double dif2 = numE-((num1/num2)*num3);
					double dif3 = numE-((num1/num2)/num3);
					if((num1/num2)-num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"x"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="x") {
					double dif = numE-((num1/num2)*num3);
					double dif1 = numE-((num1/num2)-num3);
					double dif2 = numE-((num1/num2)+num3);
					double dif3 = numE-((num1/num2)/num3);
					if((num1/num2)*num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"-"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"+"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)/num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"÷"+num3st;
					}
				}
				
				if(opSeleccionada=="÷") {
					double dif = numE-((num1/num2)/num3);
					double dif1 = numE-((num1/num2)+num3);
					double dif2 = numE-((num1/num2)-num3);
					double dif3 = numE-((num1/num2)*num3);
					if((num1/num2)/num3<numE) {
						esCorrecta = true;
						if((dif <= dif1 || dif1 <= 0) && (dif <= dif2 || dif2 <= 0) && (dif <= dif3 || dif3 <= 0)) {
							esMasCercana = true;
						}
					}
					if((dif1 < dif || dif <= 0) && (dif1 < dif2 || dif2 <= 0) && (dif1 < dif3 || dif3 <= 0) && dif1 > 0) {
						numR = (num1/num2)+num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"+"+num3st;
					}
					if((dif2 < dif || dif <= 0) && (dif2 < dif1 || dif1 <= 0) && (dif2 < dif3 || dif3 <= 0) && dif2 > 0) {
						numR = (num1/num2)-num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"-"+num3st;
					}
					if((dif3 < dif || dif <= 0) && (dif3 < dif2 || dif2 <= 0) && (dif3 < dif1 || dif1 <= 0) && dif3 > 0) {
						numR = (num1/num2)*num3;
						num1st = String.format("%.0f",num1);
						num2st = String.format("%.0f",num2);
						num3st = String.format("%.0f",num3);
						operacionR = "("+num1st+"÷"+num2st+")"+"x"+num3st;
					}
				}
			}
		}	
		
		
		Object[] condiciones = new Object[4];
		condiciones[0] = esCorrecta;
		condiciones[1] = esMasCercana;
		condiciones[2] = numR;
		condiciones[3] = operacionR;
		
		return condiciones;
	}
	
}
