
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../comunes/inicioHTML.jsp">
    <jsp:param name="elTitulo" value="Ver a ${alumnoAVer.nombreCompleto}" />
</jsp:include>

<jsp:include page="../comunes/navbar.jsp" />


<section class="container py-3">
    <div class="row">
        <h1 class="h3">Viendo alumno</h1>
        <p class="lead">Estás viendo los datos de ${alumnoAVer.nombreCompleto}. Tambien podes clickear en modificar y borrar desde aca.</p>
        
    </div>

    <div class="row align-items-center px-4 mt-3">
        <div class="col-md-5 col-lg-4" >
            <div class="card h-100">
                <jsp:include page="partes/dataCardAlumno.jsp">
                    <jsp:param name="fotoAlumno" value="${alumnoAVer.foto}" />
                    <jsp:param name="nombreCompletoAlumno" value="${alumnoAVer.nombreCompleto}" />
                </jsp:include>
            </div>
        </div>
        
        <div class="col-md-7 col-lg-8" >
            <form id="formAgregarAlumno" action="${pageContext.request.contextPath}/app?accion=update&id=${alumnoAVer.id}"
                  method="post" class="was-validated border p-4 rounded-3 bg-light">
                <div class="row">
                    <div class="col-sm-6 mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${alumnoAVer.nombre}" readonly>
                    </div>
                    <div class="col-sm-6 mb-3">
                        <label for="apellido" class="form-label">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" value="${alumnoAVer.apellido}" readonly>
                    </div>
                    <div class="col-sm-6 mb-3">
                        <label for="mail" class="form-label">Mail</label>
                        <input type="text" class="form-control" id="mail" name="mail" value="${alumnoAVer.mail}" readonly>
                    </div>
                    <div class="col-sm-6 mb-3">
                        <label for="fechaNac" class="form-label">Fecha de nacimiento</label>
                        <input type="date" class="form-control" id="fechaNac" name="fechaNac" value="${alumnoAVer.fechaNacimiento}" readonly>
                    </div>

                </div>
                <div class="row justify-content-end mt-2">
                    <div class="col-12">
                        <div class="float-end">
                            <a href="${pageContext.request.contextPath}/app" class="btn btn-secondary">Volver atrás</a>
                        </div>
                        <div class="col-2 ">
                        <a href="${pageContext.request.contextPath}/app?accion=edit&id=${alumnoAVer.id}" class="btn bg-warning w-100">Modificar</a>
                        </div>   
                        <div class="col-2 mt-2">
                            <a href="${pageContext.request.contextPath}/app?accion=remove&id=${alumnoAVer.id}" class="btn bg-danger text-light w-100">Borrar</a>
                        </div>                 
                    </div>
                </div>
            </form>
        </div>
                      
    </div>

</section>

<script src="scripts/fotobase64.js"></script>
<jsp:include page="../comunes/footer.jsp"/>
<jsp:include page="../comunes/finHTML.jsp"/>