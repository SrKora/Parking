package org.example.parking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Coche {
    protected final String matricula;
    protected LocalDateTime horaEntrada;
    protected LocalDateTime horaSalida;
    protected float deuda;
    protected int residente; // 0 = oficiales / 1 = residentes / 2 = no residentes

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Coche(String matricula, int residente) {
        this.matricula = matricula;
        this.horaEntrada = LocalDateTime.now();
        this.residente = residente;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public float getDeuda() {
        return deuda;
    }

    public void setDeuda() {
        switch (residente) {
            case 0:
                this.deuda = 0;
                break;
            case 1:
                this.deuda += (float) (calcularTiempoMin(horaEntrada, horaSalida) * 0.002);
                break;
            case 2, 3:
                this.deuda = (float) (calcularTiempoMin(horaEntrada, horaSalida) * 0.02);
                break;
            default:
                break;
        }
    }

    public int getResidente() {
        return residente;
    }

    public void setResidente(int residente) {
        this.residente = residente;
    }

    @Override
    public String toString() {
        String cadena = "Matrícula: " + matricula;
        cadena += " Hora Entrada: " + horaEntrada.format(formato);
        return cadena;
    }

    public String toStringSinFecha() {
        String cadena = "Matrícula: " + matricula;
        return cadena;
    }

    public String toStringConResidente() {
        String cadena = "Matrícula: " + matricula;
        cadena += " Hora Entrada: " + horaEntrada.format(formato);
        switch (residente) {
            case 0:
                cadena += " Tipo de vehiculo: Oficial";
                break;
            case 1:
                cadena += " Tipo de vehiculo: Residente";
                break;
            case 2:
                cadena += " Tipo de vehiculo: No residente";
                break;
        }
        return cadena;
    }

    public int calcularTiempoMin(LocalDateTime horaEntrada, LocalDateTime horaSalida) {
        int minutosEntrada = (horaEntrada.getMonthValue() * 24 * 60) + (horaEntrada.getDayOfMonth() * 24 * 60) + (horaEntrada.getHour() * 60 + horaEntrada.getMinute());
        int minutosSalida = (horaSalida.getMonthValue() * 24 * 60) + (horaSalida.getDayOfMonth() * 24 * 60) + (horaSalida.getHour() * 60 + horaSalida.getMinute());
        return minutosSalida - minutosEntrada;
    }
}