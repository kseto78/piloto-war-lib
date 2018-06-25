package es.minhap.misim.bus.core.pojo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.w3c.dom.Document;

import es.redsara.intermediacion.scsp.esquemas.v3.peticion.Peticion;
import es.redsara.intermediacion.scsp.esquemas.v3.peticion.SolicitudTransmision;
import es.redsara.intermediacion.scsp.esquemas.v3.peticion.Solicitudes;

public class PeticionPayload extends SoapPayload<Peticion> {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -3618994688806837865L;

	@Override
	public List<SoapPayload<Peticion>> getSubQueries(final String encoding) throws Exception {

		// Resultado
		List<SoapPayload<Peticion>> subQueries = null;

		// Convierte el mensaje SOAP de origen en Peticion
		final Peticion peticion = XMLUtils.getPayloadFromSOAPBody(this.getSoapMessage(), Charset.forName(encoding),
				Peticion.class);

		if (peticion != null && peticion.getSolicitudes() != null
				&& peticion.getSolicitudes().getSolicitudTransmision() != null) {

			// Por cada solicitud
			for (final SolicitudTransmision solicitudTransmision : peticion.getSolicitudes().getSolicitudTransmision()) {

				// Se crea una subpetición
				final Peticion subPeticion = new Peticion();
				// Los atributos son iguales
				subPeticion.setAtributos(peticion.getAtributos());
				// Creamos un nodo solicitudes
				subPeticion.setSolicitudes(new Solicitudes());
				subPeticion.getSolicitudes().setId(peticion.getSolicitudes().getId());
				// Añadimos el nodo solicitudTransmision
				subPeticion.getSolicitudes().getSolicitudTransmision().add(solicitudTransmision);
				// Convertimos la subpetición a SOAP (DOM)
				final Document subDOMPeticion = XMLUtils.setPayloadFromObject(subPeticion, Charset.forName(encoding),
						Peticion.class);
				// Instanciamos un SOAPPayload
				final SoapPayload<Peticion> subSOAPPeticion = new PeticionPayload();
				subSOAPPeticion.setSoapAction(this.getSoapAction());
				subSOAPPeticion.setSoapMessage(subDOMPeticion);
				// Añadimos la subpeticion a la lista de consultas
				if (subQueries == null) {
					subQueries = new ArrayList<SoapPayload<Peticion>>();
				}
				subQueries.add(subSOAPPeticion);

			}
		}

		return subQueries;
	}

}
