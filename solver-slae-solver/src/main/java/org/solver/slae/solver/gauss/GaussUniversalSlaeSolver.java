package org.solver.slae.solver.gauss;

import org.solver.slae.solver.universal.UniversalSlaeSolver;
import org.springframework.stereotype.Component;

@Component("gaussUniversalSlaeSolver")
public class GaussUniversalSlaeSolver implements UniversalSlaeSolver {
	
	public double[] solve(double[][] matrix, double[] vector) {
		int n = vector.length;
        //прямий хід
        for (int t = 0; t < n - 1; t++) {
            int p = t;
            int i = t;
            //шукаємо головний елемент у стовпці
            for (; i < n; i++) {
                if (Math.abs(matrix[i][t]) > Math.abs(matrix[p][t])) p = i;
            }
            if (p != t)
            {
            	double temp = vector[t];
            	vector[t] = vector[p];
            	vector[p] = temp;
                for (int k = t; k < n; ++k)
                {
                    temp = matrix[t][k];
                    matrix[t][k] = matrix[p][k];
                    matrix[p][k] = temp;
                }
            }
            for (int k = t + 1; k < n; ++k) {
                if (matrix[k][t] != 0) {
                	vector[k] -= vector[t]*matrix[k][t]/matrix[t][t];
                    for (int j = t + 1; j < n; j++) {
                        matrix[k][j] -= matrix[t][j]*matrix[k][t]/matrix[t][t];
                    }
                    matrix[k][t] = 0;
                }
            }
        }
        //зворотній хід
        double[] coefficients = new double[n];
        for (int k = n - 1; k >= 0; --k) {
            double temp = 0;
            for (int j = k + 1; j < n; ++j) {
                temp += matrix[k][j]*coefficients[j];
            }
            coefficients[k] = (vector[k] - temp)/matrix[k][k];
        }
        return coefficients;
    }

}
