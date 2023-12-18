package bsu.rfe.jave.group7.lab3.Dybal.varA10;

import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return Double.valueOf(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {
        double result = 0.0;
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;
        if (col==0) {
// Если запрашивается значение 1-го столбца, то это X
            return x;
        } else if(col ==1){
// Если запрашивается значение 2-го столбца, то это значение
// многочлена

            for(int i = 0; i < coefficients.length; i++){
                result += coefficients[i] * Math.pow(x,coefficients.length-i);
            }
// Вычисление значения в точке по схеме Горнера.
// Вспомнить 1-ый курс и реализовать
            return result;
        }else{
            boolean isSquare = false;
            int temp = (int)Math.sqrt(result);
            if(Math.pow(temp,2) == result && Math.pow(temp,2)!=0){
                isSquare = true;
            }
                if(isSquare)
                    return 1;
                else
                    return 0;
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            default:
                return "Является ли целая часть квадратом";
        }
    }
    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}