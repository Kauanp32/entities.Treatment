package entities.Treatment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Model.Treatment.DomainException;

public class Reservation {

    private Integer roomNumber;  // Número do quarto
    private Date checkIn;  // Data de check-in
    private Date checkOut;  // Data de check-out
    
    // Define o formato de data estático que será utilizado em toda a classe
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    // Construtor da classe que inicializa os atributos e valida as datas de check-in e check-out
    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        // Verifica se a data de check-out é posterior à data de check-in
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getter para o número do quarto
    public Integer getRoomNumber() {
        return roomNumber;
    }

    // Setter para o número do quarto
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Getter para a data de check-in
    public Date getCheckIn() {
        return checkIn;
    }

    // Getter para a data de check-out
    public Date getCheckOut() {
        return checkOut;
    }

    // Método para calcular a duração da estadia em noites
    public long duration() {
        // Calcula a diferença em milissegundos entre check-in e check-out
        long diff = checkOut.getTime() - checkIn.getTime();
        // Converte a diferença de milissegundos para dias
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    // Método para atualizar as datas de check-in e check-out
    public void updateDates(Date checkIn, Date checkOut) {
        Date now = new Date();  // Data atual
        // Verifica se as novas datas são futuras em relação à data atual
        if (checkIn.before(now) || checkOut.before(now)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        // Verifica se a data de check-out é posterior à data de check-in
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    
    // Método para gerar uma representação em string da reserva
    @Override
    public String toString() {
        return "Room "
            + roomNumber  // Número do quarto
            + ", check-in: "
            + sdf.format(checkIn)  // Formata a data de check-in
            + ", check-out: "
            + sdf.format(checkOut)  // Formata a data de check-out
            + ", "
            + duration()  // Duração da estadia em noites
            + " nights";
    }
}