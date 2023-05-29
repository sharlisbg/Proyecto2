(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
    
    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      $("body").addClass("sidebar-toggled");
      $(".sidebar").addClass("toggled");
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });
  

	//Evento del botón que me devuelve el listado de libros por autor
	$("#btn-buscar-autor").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/Seccion40Grupo7/BookByAuthor?book_name=' + $('#txt-buscar-ingrese-autor').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlAutorList = '<ul>';
				$.each(data.libros, function(i,item){
          htmlAutorList += '<li>' + item + '</li>';
				});
				htmlAutorList += '</ul>';
				$('#').html("");
				$('#div-listado-libros-por-autor').append(htmlAutorList);
			}
		} );
		
		
	});



	//Evento del botón que me devuelve el listado de libros por último leído
	$("#btn-buscar-ultimo").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/Seccion40Grupo7/BookByLastRead?book_name=' + $('#txt-buscar-ingrese-nombre-libro').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlLastReadList = '<ul>';
				$.each(data.libros, function(i,item){
          htmlLastReadList += '<li>' + item + '</li>';
				});
				htmlLastReadList += '</ul>';
				$('#').html("");
				$('#div-listado-libros-ultimo-leido').append(htmlLastReadList);
			}
		} );
		
		
	}); 



  	//Evento del botón que me devuelve el listado de libros por genero
	$("#btn-buscar-genero").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/Seccion40Grupo7/BookByGenero?book_name=' + $('#txt-buscar-ingrese-genero-libro').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlGenreList = '<ul>';
				$.each(data.generos, function(i,item){
          htmlGenreList += '<li>' + item + '</li>';
				});
				htmlGenreList += '</ul>';
				$('#').html("");
				$('#div-listado-libros-por-genero').append(htmlGenreList);
			}
		} );
		
		
	}); 



	
	
	//Evento del botón que creara una nueva persona en el grafo
	$("#guardar-usuario-btn").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/Seccion40Grupo7/SavePersonServlet?name=' + $('#txt-usuario-nombre').val(),
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});

  //Evento del botón que creara un nuevo libro
	$("#guardar-libro-btn").click(function(){
		
		$.ajax( {
			
			type: "GET",
			url: '/Seccion40Grupo7/SaveBookServlet?author=' + $('#txt-ingrese-autor').val() + '&name=' + $('#txt-ingrese-nombre').val() + '&editorial=' + $('#txt-ingrese-editorial').val() + '&genre=' + $('#txt-ingrese-genero').val() ,
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});



  //Evento del botón que creara una nueva relación
	$("#ingresar-usuario-btn").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/Seccion40Grupo7/SaveRelationshipServlet?personName=' + $('#txt-nombre-usuario-ingreso').val() + '&bookName=' + $('#txt-nombre-libro-ingrese').val() + '&rating=' + $('#txt-rating-libro-ingrese').val() ,
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});



})(jQuery); // End of use strict
