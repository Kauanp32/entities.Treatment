package entities.Treatment;

// Importa a classe BusinessException do pacote Model.Treatment
import Model.Treatment.BusinessException;

// Declaração da classe Account
public class Account {

    // Declaração dos atributos da conta
    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;
    
    // Construtor padrão da classe Account
    public Account() {
    }

    // Construtor com parâmetros para inicializar os atributos da conta
    public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    // Métodos getters e setters para acessar e modificar os atributos da conta
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(Double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    // Método para realizar um depósito na conta
    public void deposit(double amount) {
        balance += amount;
    }

    // Método para realizar um saque na conta
    public void withdraw(double amount) {
       
        validateWithdraw(amount);
       
        // Realiza o saque subtraindo o valor do saldo
        balance -= amount;
    }

    // Método privado para validar se o saque é possível
    private void validateWithdraw(double amount) {
        
    	// Verifica se o valor do saque excede o limite de saque
        if (amount > getWithdrawLimit()) {
            throw new BusinessException("Erro de saque: A quantia excede o limite de saque");
        } 
        // Verifica se o valor do saque é maior que o saldo disponível na conta
        if (amount > getBalance()) {
            throw new BusinessException("Erro de saque: Saldo insuficiente");
        }
    }
}