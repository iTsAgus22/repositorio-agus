package com.persistenciaJPA.TP1;

import com.persistenciaJPA.TP1.entidades.*;
import com.persistenciaJPA.TP1.repositorios.ClienteRepository;
import com.persistenciaJPA.TP1.repositorios.RubroRepository;
import com.persistenciaJPA.TP1.enums.Tipo;
import com.persistenciaJPA.TP1.enums.TipoEnvio;
import com.persistenciaJPA.TP1.enums.Estado;
import com.persistenciaJPA.TP1.enums.FormaPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Calendar;


@SpringBootApplication
public class Tp1Application {
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public Tp1Application(){
	}

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			// Crear instancias de los objetos

			Rubro rubro = Rubro.builder()
					.denominacion("Comida Rápida")
					.build();

			Producto producto = Producto.builder()
					.tipo(Tipo.Insumo)
					.tiempoEstimadoCocina(10)
					.denominacion("Hamburguesa")
					.precioVenta(1000)
					.precioCompra(800)
					.stockActual(20)
					.stockMinimo(5)
					.unidadMedida("Unidades")
					.receta("Pan de hamburguesa, lechuga, tomate, queso, condimentos (a elección)")
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Carlitos")
					.apellido("Gomez")
					.email("CGomez@gmail.com")
					.telefono("2619854533")
					.build();

			Domicilio domicilio1 = Domicilio.builder()
					.calle("San Martin")
					.numero("867")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Belgrano")
					.numero("556")
					.build();

			cliente.agregarDomicilio(domicilio1); //Agrego los posibles domicilios a cliente
			cliente.agregarDomicilio(domicilio2);

			DetallePedido detallePedido = DetallePedido.builder()
					.cantidad(3)
					.subtotal(350)
					.producto(producto)
					.build();

			Calendar calendar = Calendar.getInstance();
			calendar.set(2023,1,1);

			Pedido pedido1 = Pedido.builder()
					.estado(Estado.Iniciado)
					.fecha(calendar.getTime())
					.tipoEnvio(TipoEnvio.Delivery)
					.total(1000)
					.build();

			/*
			Pedido pedido2 = Pedido.builder()    				//Si quisieramos crear mas pedidos
					.estado(Pedido.Estado.Preparacion)
					.fecha(new Date())
					.tipoEnvio(Pedido.TipoEnvio.Delivery)
					.total(3000)
					.build();
			*/

			Factura factura = Factura.builder()
					.numero(1)
					.fecha(calendar.getTime())
					.descuento(100)
					.formaPago(FormaPago.Efectivo)
					.total(1500)
					.build();

			cliente.agregarPedido(pedido1); //agregamos un pedido a un cliente
			//cliente.agregarPedido(pedido2); //En caso de tener más de un pedido
			pedido1.agregarDetallePedido(detallePedido); //Agregamos el detallepedido a un pedido
			rubro.agregarProducto(producto); //agregamos un producto a un rubro
			pedido1.setFactura(factura);
			//Guardamos los objetos creados
			rubroRepository.save(rubro);
			clienteRepository.save(cliente);

			// Recuperar el objeto Rubro desde la base de datos ya que no se puede acceder a el por claves foraneas y navegabilidad desde cliente.
			Rubro rubrorec = rubroRepository.findById(rubro.getId()).orElse(null);
			if (rubrorec != null){
				System.out.println("------------------------------------------------------");
				System.out.println("Se encontró un rubro con los siguientes datos:");
				System.out.println("------------------------------------------------------");
				System.out.println("Denominación: " + rubrorec.getDenominacion());
				rubro.mostrarProductos();
			}
			// Recuperar el objeto Cliente desde la base de datos

			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);

			// A partir del cliente podemos navegar por diferentes clases gracias a las claves foráneas

			//Imprimimos los datos asociados al cliente en pantalla
			if (clienteRecuperado != null) {
				System.out.println("------------------------------------------------------");
				System.out.println("Se encontró un cliente con los siguientes datos: ");
				System.out.println("------------------------------------------------------");
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				System.out.println("Teléfono: " + clienteRecuperado.getTelefono());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

				for (Pedido pedidoRecuperado1 : clienteRecuperado.getPedidos()){
					if (pedidoRecuperado1.getFactura() != null){
						System.out.println("------------------------------------------------------");
						System.out.println("Se encontro una factura con los siguientes detalles: ");
						System.out.println("------------------------------------------------------");
						System.out.println("Número: " + pedidoRecuperado1.getFactura().getNumero());
						System.out.println("Fecha: " + pedidoRecuperado1.getFactura().getFecha());
						System.out.println("Descuento: " + pedidoRecuperado1.getFactura().getDescuento());
						System.out.println("Forma de pago: " + pedidoRecuperado1.getFactura().getFormaPago());
						System.out.println("Total: " + pedidoRecuperado1.getFactura().getTotal());
						pedidoRecuperado1.mostrarDetallePedidos();
					}
					for (DetallePedido detallePedido1 : pedidoRecuperado1.getDetallePedidos()){
						System.out.println("------------------------------------------------------");
						System.out.println("El producto ordenado es el siguiente: ");
						System.out.println("------------------------------------------------------");
						System.out.println("Tipo: " + detallePedido1.getProducto().getTipo());
						System.out.println("Tiempo estimado de cocina: " + detallePedido1.getProducto().getTiempoEstimadoCocina());
						System.out.println("Denominación: " + detallePedido1.getProducto().getDenominacion());
						System.out.println("Precio de venta: " + detallePedido1.getProducto().getPrecioVenta());
						System.out.println("Precio de compra: " + detallePedido1.getProducto().getPrecioCompra());
						System.out.println("Stock actual: " + detallePedido1.getProducto().getStockActual());
						System.out.println("Stock mínimo: " + detallePedido1.getProducto().getStockMinimo());
						System.out.println("Unidad de medida: " + detallePedido1.getProducto().getUnidadMedida());
						System.out.println("Receta: " + detallePedido1.getProducto().getReceta());
					}
				}
			}

		};

	}

}
