package br.furb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Cenários do Sistema Bancário:
 * 1. Criação de Conta:
 * Dado que o sistema bancário está operacional,
 * Quando um cliente cria uma nova conta com um depósito inicial,
 * Então a conta deve ser criada com sucesso e o saldo deve ser igual ao depósito inicial.
 *
 * 2. Consulta de Saldo:
 * Dado que existe uma conta com um saldo conhecido,
 * Quando o cliente consulta o saldo dessa conta,
 * Então o sistema deve retornar o valor exato do saldo.
 *
 * 3. Depósito:
 * Dado que existe uma conta com um saldo inicial,
 * Quando o cliente deposita um valor nessa conta,
 * Então o saldo da conta deve ser atualizado, somando o valor do depósito.
 *
 * 4. Saque:
 * Dado que existe uma conta com saldo suficiente,
 * Quando o cliente saca um valor dessa conta,
 * Então o saldo da conta deve ser atualizado, subtraindo o valor do saque.
 *
 * 5. Transferência:
 * Dado que existem duas contas, uma de origem com saldo suficiente e uma de destino,
 * Quando o cliente transfere um valor da conta de origem para a de destino,
 * Então o saldo da conta de origem deve ser debitado e o saldo da conta de destino deve ser creditado com o valor da transferência.
 */
@DisplayName("Bank system tests")
public class BankTests {
    double numeroContaMock = 12345;
    double saldoInicialContaMock = 200.20;
    ContaBancaria contaBancaria;

    public BankTests() {
        this.contaBancaria = new ContaBancaria();
    }

    @Test
    public void criacaoContaTest() {        
        this.contaBancaria.criarConta(this.numeroContaMock, this.saldoInicialContaMock);

        double saldoContaEspecifica = this.contaBancaria.obterSaldoConta(this.numeroContaMock);
        assertEquals(saldoContaEspecifica, this.saldoInicialContaMock);
    }

    @Test
    public void consultaSaldoContaTest() {
        this.contaBancaria.criarConta(this.numeroContaMock, this.saldoInicialContaMock);
        double saldoConta = this.contaBancaria.obterSaldoConta();

        assertEquals(saldoConta, this.contaBancaria.obterSaldoConta());
    }

    @Test
    public void depositarValorContaTest() {
        this.contaBancaria.criarConta(this.numeroContaMock, this.saldoInicialContaMock);
        double valorDeposito = 300.00;
        double saldoOriginalConta = this.contaBancaria.obterSaldoConta();

        double saldoAtual = this.contaBancaria.depositar(valorDeposito);

        assertEquals(saldoAtual, valorDeposito + saldoOriginalConta);
    }

    @Test
    public void sacarValorContaTest() {
        this.contaBancaria.criarConta(this.numeroContaMock, this.saldoInicialContaMock);
        double valorSaque = 200.00;
        double valorOriginalConta = this.contaBancaria.obterSaldoConta();

        double saldoAtual = this.contaBancaria.sacar(valorSaque);

        assertEquals(saldoAtual, valorOriginalConta - valorSaque);
    }

    @Test
    public void transferenciaTest() {
        this.contaBancaria.criarConta(this.numeroContaMock, this.saldoInicialContaMock);
        ContaBancaria contaAuxiliar = new ContaBancaria();
        contaAuxiliar.numeroConta = 45678;
        contaAuxiliar.saldoInicial = 300.00;
        double valorTransferencia = 100.00;

        double valorAtualContaOrigem = this.contaBancaria.transferir(contaAuxiliar, valorTransferencia);

        assertEquals(valorAtualContaOrigem, this.contaBancaria.obterSaldoConta() - valorTransferencia);
    }
}
