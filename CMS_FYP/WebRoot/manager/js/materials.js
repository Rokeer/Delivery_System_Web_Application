$(function() {
	$('#mainTable').datagrid( {
		title : 'Materials',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getMaterialsList.action', //action地址  
		sortName : 'mId',
		sortOrder : 'desc',
		idField : 'mId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'mId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'mName',
			title : 'Name',
			width : 120
		}, {
			field : 'mAmount',
			title : 'Amount',
			width : 120
		},

		] ],
		pagination : true, //包含分页  
		singleSelect : true,
		toolbar : [ {
			text : 'Add',
			iconCls : 'icon-add',
			handler : function() {
				$('#addDialog').dialog('open');
			}
		}, {
			text : 'Edit',
			iconCls : 'icon-remove',
			handler : function() {
				var selected = $('#mainTable').datagrid('getSelected');
				if (selected){
					$('#editDialog').dialog('open');
					$('#mId').val(selected.mId);
					$('#mName').val(selected.mName);
					$('#mAmount').val(selected.mAmount);
				}
			}
		}, '-', {
			text : 'Delete',
			iconCls : 'icon-cancel',
			handler : function() {
				var selected = $('#mainTable').datagrid('getSelected');
				if (selected){
					$.messager.confirm('Confirm', 'Are you confirm to delete '+selected.mName+'?', function(r){
						if (r){
							$('#delMId').val(selected.mId);
							$('#delForm').submit(); 
						}
					});
				}
				
			}
		} ]
	});
	
	$('#addDialog').dialog({
		title : 'New Material',
    	modal : true, 
    	closed : true
	}); 
	
	$('#editDialog').dialog({
		title : 'Edit Material',
    	modal : true, 
    	closed : true
	}); 
	
	$('#addForm').form({  
    url:'../json/addMaterial.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
    		$('#addDialog').dialog('close');
        	$.messager.show({
				title:'Success!',
				msg:'A kind of new material be added!',
				timeout:4000,
				showType:'slide'
			});
        	$('#addForm').form('clear');
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	$('#editForm').form({  
    url:'../json/editMaterial.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
    		$('#editDialog').dialog('close');
        	$.messager.show({
				title:'Success!',
				msg:'A kind of material be edited!',
				timeout:4000,
				showType:'slide'
			});
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	$('#delForm').form({  
    url:'../json/delMaterial.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
        	$.messager.show({
				title:'Success!',
				msg:'A kind of material be deleted!',
				timeout:4000,
				showType:'slide'
			});
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
});

function submitAddForm(){
	if(isNaN(document.addForm.mAmount.value)||document.addForm.mAmount.value<=0){
   		alert('Amount must be a positive number!')
   		document.addForm.mAmount.focus();
   		return false;
	}
	if(!document.addForm.mName.value || !document.addForm.mAmount.value){
		alert('Please fill in the blank!');
		return false;
	}
	// submit the form  
	$('#addForm').submit();  	
};

function submitEditForm(){
	if(isNaN(document.editForm.mAmount.value)||document.editForm.mAmount.value<=0){
   		alert('Amount must be a positive number!')
   		document.addForm.mAmount.focus();
   		return false;
	}
	if(!document.editForm.mName.value || !document.editForm.mAmount.value){
		alert('Please fill in the blank!');
		return false;
	}
	// submit the form  
	$('#editForm').submit(); 
};