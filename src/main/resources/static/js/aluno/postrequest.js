
$(function(){
	
	loadList();
	

	
	
	$("#formAluno").submit(function(event){
		event.preventDefault();
		
		ajaxPost();
	});
	
	
	function ajaxPost(){
		var formDataAluno = {
			matricula: $("#matricula").val(),
			nome: $("#nome").val(),
			email: $("#email").val(),
			password: $("#password").val(),
			sexo: $("input[name='sexo']").val(),
			dataNascimento: $("#dataNascimento").val(),
			fluxoNormal: $("#fluxoNormal1").val(),
			bolsa: $("input[name='tipoDeAluno']").val()
		};
		
		$.ajax({
			type: "POST",
			url: '/api/aluno/salvar',
			data: JSON.stringify(formDataAluno),
			contentType: 'application/json',
			dataType: 'json',
		}).done(function(result){
			
			console.log("result::"+JSON.stringify(result));
			
			$("#messageAlert").html("Aluno salvo com sucesso. "+
					"No registro "+result.id+", está cadastrado "+ result.nome + "."
			);
			
			
			
			$('#formAluno').trigger("reset");
			
			$("#alertTop").show();
			$("#alertTop").toggleClass('show');
			
			loadList();
			
			
		}).fail(function(xhr, status, errorThrown){
			
		});
		
	}
	
	function loadList(){
		$.get("/api/aluno/listar", function(response){
			var contentHtml = "";
			$.each(response, function(i, aluno){
				console.log(i+' == '+ JSON.stringify(aluno));
				
				
				var row = "<tr>"+
							"<td>"+i+"</td>"+
							"<td>"+aluno.id+"</td>"+
							"<td>"+aluno.nome+"</td>"+
							"<td>"+aluno.dataNascimento+"</td>"+
							"<td>"+aluno.matricula+"</td>"+
							"<td>"+aluno.matricula+"</td>"+
							"<td>"+
								"<a href='/aluno/edit-"+aluno.id+"-aluno'>edit</a>"+
							"</td>"+
						  "</tr>";
				
				contentHtml += row;
				
			});
			
			$("#resultList").html(contentHtml);
		});
		
	} 
	
	
});