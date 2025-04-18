$(function(){

	$(".file-dropzone").on('dragover', handleDragEnter);
	$(".file-dropzone").on('dragleave', handleDragLeave);
	$(".file-dropzone").on('drop', handleDragLeave);

	function handleDragEnter(e) {

		this.classList.add('drag-over');
	}

	function handleDragLeave(e) {

		this.classList.remove('drag-over');
	}

	// "dropzoneForm" is the camel-case version of the form id "dropzone-form"
	Dropzone.options.dropzoneForm = {

		url : "/board/setUploadFtp.info",
		autoProcessQueue : false,
		uploadMultiple : true,
		maxFilesize : 50, // MB
		parallelUploads : 100,
		maxFiles : maxupFile,
		addRemoveLinks : true,
		acceptedFiles: "image/*,video/*,.zip,.hwp,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.pdf,.txt",
		previewsContainer : ".dropzone-previews",

		// The setting up of the dropzone
		init : function() {
			
			var myDropzone = this;
			var objFrm = document.frm;
			
			var fileCount = 0;
			var fileSize = 0;
			
			var thisDropzone = this;
            this.on("maxfilesexceeded", function (file) {
                this.removeFile(file);
				 $("#fileCountTxt").text(fileCount +"장");
                alert("파일 갯수 초과로 제거되었습니다");
            });

			// first set autoProcessQueue = false
			$('#upload-button').on("click", function(e) {
				fileFlag = myDropzone.processQueue();
			});
			
			this.on("addedfile", function (file) {
				 fileCount++;		 
				 fileSize += file.size
				 $("#fileCountTxt").text(fileCount +"개");
				 $("#totalSizeTxt").html(this.filesize(fileSize));
            });
			 
			 this.on("removedfile", function (file) {
				 fileCount--;
				 fileSize -= file.size
				 $("#fileCountTxt").text(fileCount +"개");
				 $("#totalSizeTxt").html(this.filesize(fileSize));
				 if (fileCount == 0){

					 var sClass = $("#guide_text").attr('class');

					 if(sClass.indexOf('nobg') >= 0){
			   			$("#guide_text").removeClass('nobg');
			   			$("#guide_text").addClass('bg');
			   		}
				 }

            });
			
			// customizing the default progress bar
			this.on("uploadprogress", function(file, progress) {
				progress = parseFloat(progress).toFixed(0);

				var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
				progressBar.innerHTML = progress + "%";
			});

			// displaying the uploaded files information in a Bootstrap dialog
			this.on("successmultiple", function(files, serverResponse) {
				var responseContent = "";
				
				if (typeof(execAttach) == 'undefined') 
			    { 
			        //Virtual Function
			        return;        
			    }
				
				var _mockdata = new Array(); //배열 선언

				for (var i = 0; i < serverResponse.length; i++) {
					var imageurl,filename,filesize,originalname,thumburl,fileext;
					
					var infoObject = serverResponse[i];

					for ( var infoKey in infoObject) {
						if (infoObject.hasOwnProperty(infoKey)) {
							switch(infoKey) {
							    case "name":
								    filename = infoObject[infoKey];
								    break;
							    case "serverUrl":
							    	imageurl = infoObject[infoKey];
							    	break;
							    case "originalName":
							    	originalname = infoObject[infoKey];
							    	break;
							    case "size":
							    	filesize = infoObject[infoKey];
							    	break;
							    case "type":
							    	fileext = infoObject[infoKey];
							    	break;
							    	
							}
						}
					}
					
					_mockdata[i] = {
				        'attachurl': "http://localhost:8081"+imageurl + filename,
				        'filename': filename,
				        'filesize': filesize,
				        'fileurl': imageurl,
				        'originalname': originalname,
				        'fileext' : fileext,
				        'fileseq' : 0
				    };
					execAttach(_mockdata[i]);
				}
				closeWindow();
			});
		}
	}

});