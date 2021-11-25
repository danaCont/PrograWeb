package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.PaymentMethod;



public interface IPaymentMethodService {

	public boolean insertar (PaymentMethod paymentMethod);
	public boolean modificar (PaymentMethod paymentMethod);
	public void eliminar (int idPaymentMethod);
	public Optional<PaymentMethod> listarId(int idPaymentMethod);
	List<PaymentMethod> listar();
	List<PaymentMethod> buscarName (String name);
	
}