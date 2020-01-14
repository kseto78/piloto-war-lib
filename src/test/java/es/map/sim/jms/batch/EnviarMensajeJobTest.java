package es.map.sim.jms.batch;


public class EnviarMensajeJobTest {
	
	/*private EnviarMensajeJob job;
	@Before
	public void init(){
		job= new EnviarMensajeJob();
		job.setEnviarMensajeMessageReceiver(Mockito.mock(SIMMessageReceiver.class));
		job.setMaxMensajesLeer(5);
		job.setPlanificacionesManager(Mockito.mock(TblPlanificacionesManager.class));
		job.setServiciosManager(Mockito.mock(TblServiciosManager.class));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExecuteNullPlanificacionesManager() throws JobExecutionException {
		job.setPlanificacionesManager(null);
		job.execute();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExecuteNullServiciosManager() throws JobExecutionException {
		job.setServiciosManager(null);
		job.execute();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExecuteNullEnviarMensajeReceiver() throws JobExecutionException {
		job.setEnviarMensajeMessageReceiver(null);
		job.execute();
	}
	@Test
	public void testExecuteMaximoMensajeNegativo() throws JobExecutionException {
		job.setMaxMensajesLeer(-1);
		job.execute();
		assertEquals(Long.MAX_VALUE,job.getMaxMensajesLeer());
	}
	@Test
	public void testNoPlanificaciones() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(null);
		job.execute();
		verify(job.getServiciosManager(),never()).getServicio(anyLong());
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(new ArrayList<Long>());
		job.execute();
		verify(job.getServiciosManager(),never()).getServicio(anyLong());
	}
	
	@Test
	public void testOnePlanificacionNoServicio() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(null);
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),never()).receiveByServiceName(anyString());
	}
	@Test
	public void testOneServicioNullNombre() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(new TblServicios());
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),never()).receiveByServiceName(anyString());
	}
	@Test
	public void testOneServicioEmtpyNombre() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		TblServicios tblServicio = new TblServicios();
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(tblServicio);
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),never()).receiveByServiceName(anyString());
	}
	@Test
	public void testExceptionOnReceiving() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		TblServicios testServicio = new TblServicios();
		testServicio.setNombre("test");
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(testServicio);
		when(job.getEnviarMensajeMessageReceiver().receiveByServiceName(anyString())).thenThrow(new RuntimeException("ErrorReceiving Mesage")).thenReturn(false);
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),times(2)).receiveByServiceName(anyString());
	}
	
	@Test
	public void testNoReceive() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		TblServicios testServicio = new TblServicios();
		testServicio.setNombre("test");
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(testServicio);
		when(job.getEnviarMensajeMessageReceiver().receiveByServiceName(anyString())).thenReturn(false);
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),times(1)).receiveByServiceName(anyString());
	}
	@Test
	public void testOneReceived() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		TblServicios testServicio = new TblServicios();
		testServicio.setNombre("test");
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(testServicio);
		when(job.getEnviarMensajeMessageReceiver().receiveByServiceName(anyString())).thenReturn(true).thenReturn(false);
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),times(2)).receiveByServiceName(anyString());
	}
	@Test
	public void testTwoReceived() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l}));
		TblServicios testServicio = new TblServicios();
		testServicio.setNombre("test");
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(testServicio);
		when(job.getEnviarMensajeMessageReceiver().receiveByServiceName(anyString())).thenReturn(true).thenReturn(true).thenReturn(false);
		
		
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),times(3)).receiveByServiceName(anyString());
	}
	
	@Test
	public void testTwoServicesOnePerServiceReceived() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l,2l}));
		TblServicios testServicio = new TblServicios();
		testServicio.setNombre("test");
		TblServicios testServicio2 = new TblServicios();
		testServicio2.setNombre("test2");
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(testServicio).thenReturn(testServicio2);
		when(job.getEnviarMensajeMessageReceiver().receiveByServiceName(anyString())).thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(false);
		
		
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),times(4)).receiveByServiceName(anyString());
	}
	@Test
	public void testTwoServicesOneZeroZeroExceptionReceived() throws JobExecutionException{
		when(job.getPlanificacionesManager().getServiciosPlanificacion()).thenReturn(Arrays.asList(new Long[]{1l,2l}));
		TblServicios testServicio = new TblServicios();
		testServicio.setNombre("test");
		TblServicios testServicio2 = new TblServicios();
		testServicio2.setNombre("test2");
		when(job.getServiciosManager().getServicio(anyLong())).thenReturn(testServicio).thenReturn(testServicio2);
		when(job.getEnviarMensajeMessageReceiver().receiveByServiceName(anyString())).thenReturn(true).thenReturn(false).thenReturn(false).thenThrow(new RuntimeException("Error processing message")).thenReturn(false).thenReturn(false);
		job.execute();
		verify(job.getEnviarMensajeMessageReceiver(),times(6)).receiveByServiceName(anyString());
	}

*/
}
