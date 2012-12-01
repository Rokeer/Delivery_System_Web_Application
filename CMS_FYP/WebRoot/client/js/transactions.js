$(function() {
	$('#mainTable').datagrid( {
		title : 'My Orders',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getCMTransactionsListByUser.action', //action地址  
		sortName : 'cmtId',
		sortOrder : 'desc',
		idField : 'cmtId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'cmtId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'productName',
			title : 'Product',
			width : 120
		}, {
			field : 'cmtAmount',
			title : 'Amount',
			width : 120
		}, {
			field : 'cmtStatus',
			title : 'Status',
			width : 120,
			formatter : statusFormatter
		}

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
				if (selected && selected.cmtStatus==0){
					$('#editDialog').dialog('open');
					$('#cmtId').val(selected.cmtId);
					$('#cmtPId').val(selected.cmtPId);
					$('#editProductName').val(selected.productName);
					$('#editCMTAmount').val(selected.cmtAmount);
				}
			}
		}, '-', {
			text : 'Delete',
			iconCls : 'icon-cancel',
			handler : function() {
				var selected = $('#mainTable').datagrid('getSelected');
				if (selected){
					$.messager.confirm('Confirm', 'Are you confirm to delete?', function(r){
						if (r){
							$('#delCMTId').val(selected.cmtId);
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
    url:'../json/addCMTransaction.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
    		$('#addDialog').dialog('close');
        	$.messager.show({
				title:'Success!',
				msg:'A new order be added!',
				timeout:4000,
				showType:'slide'
			});
        	$('#addForm').form('clear');
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	$('#editForm').form({  
    url:'../json/editCMTransaction.action',  
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
    url:'../json/delCMTransaction.action',  
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
	
	
	
	
	
	
	
	
	
	
	
	
	$('#addCMTPId').combobox({  
        url:'../json/getProductsListWithoutPage.action',  
        valueField:'pId',  
        textField:'pName'
    });  
	
})


function submitAddForm(){
	if(isNaN(document.addForm.cmtAmount.value)||document.addForm.cmtAmount.value<=0){
   		alert('Amount must be a positive number!')
   		document.addForm.mAmount.focus();
   		return false;
	}
	if(!document.addForm.cmtPId.value || !document.addForm.cmtAmount.value){
		alert('Please fill in the blank!');
		return false;
	}
	// submit the form  
	$('#addForm').submit();  	
};

function submitEditForm(){
	if(isNaN(document.editForm.cmtAmount.value)||document.editForm.cmtAmount.value<=0){
   		alert('Amount must be a positive number!')
   		document.addForm.mAmount.focus();
   		return false;
	}
	if(!document.editForm.cmtPId.value || !document.editForm.cmtAmount.value){
		alert('Please fill in the blank!');
		return false;
	}
	// submit the form  
	$('#editForm').submit(); 
};


var status = [
		    {cmtStatus:'0',statusDisplay:''},
		    {cmtStatus:'1',statusDisplay:'Confirm'}
		];
function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].cmtStatus == value) return status[i].statusDisplay;
			}
			return value;
};