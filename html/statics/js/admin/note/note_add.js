var tags = "";
var category = "";
$(document).ready(function() {

	common.ajax({
		url: '/web/category', // 跳转到 action  

		type: 'get',
		cache: false,
		dataType: 'json',
		async: false,
		success: function(data) {
			if (data.code = 200) {
				$.each(data.result, function(i, item) {
					category += "<label class=\"btn btn-default\">";
					category += "<input type=\"radio\" name=\"category\" value=\"" + item.id + "\" />" + item.title;
					category += "</label>";
				});
			}
			$("#category").html(category);
		},
		error: function() {
			alert("异常！");
		}
	});

	common.ajax({
		url: '/web/tag', // 跳转到 action  

		type: 'get',
		cache: false,
		dataType: 'json',
		async: false,
		success: function(data) {
			if (data.code = 200) {
				$.each(data.result, function(i, item) {
					tags += "<label class=\"btn btn-default\">";
					tags += "<input type=\"checkbox\" name=\"tags\" value=\"" + item.id + "\" />" + item.title;
					tags += "</label>";
				});
			}
			$("#tags").html(tags);
		},
		error: function() {
			alert("异常！");
		}
	});

	$('#addNoteForm').bootstrapValidator({
		//        live: 'disabled',
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			title: {
				validators: {
					notEmpty: {
						message: '标题不能为空'
					}
				}
			},
			category: {
				validators: {
					notEmpty: {
						message: '请选择分类'
					}
				}
			},
			tags: {
				validators: {
					choice: {
						min: 1,
						max: 5,
						message: '请选择1-5个标签'
					}
				}
			}
		}
	});

	// Validate the form manually
	$('#validateBtn').click(function() {
		$('#addNoteForm').bootstrapValidator('validate');
	});

	$('#resetBtn').click(function() {
		$('#addNoteForm').data('bootstrapValidator').resetForm(true);
	});

	frameElement.api.button({
		name: "添加",
		callback: function() {
			CKupdate();
			save();
			return false;
		}
	}, {
		name: "取消"
	});
});
 function CKupdate() {
                for (instance in CKEDITOR.instances)
                    CKEDITOR.instances[instance].updateElement();
            }
function save() {
	$('#addNoteForm').bootstrapValidator('validate');
	if ($('#addNoteForm').data('bootstrapValidator').isValid()) {
		$.ajax({
			type: "post",
			url: "/web/note",

			data: $('#addNoteForm').serialize(),
			error: function(request) {
				alert("Connection error");
			},
			success: function(data) {
				alert("添加成功");
				parent.$("#table").bootstrapTable('removeAll');
				parent.$("#table").bootstrapTable('refresh');
				frameElement.api.close();
			}
		});
	}
	
}