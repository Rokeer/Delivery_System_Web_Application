$(function() {
	$('#mainTable').datagrid( {
		title : 'Orders',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getMOrdersList.action', //action地址  
		sortName : 'moId',
		sortOrder : 'desc',
		idField : 'moId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'moId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'materialName',
			title : 'Material',
			width : 120
		}, {
			field : 'moAmount',
			title : 'Amount',
			width : 120
		}, {
			field : 'supplierName', 
			title : 'Supplier',
			width : 120
		}, {
			field : 'moStatus',
			title : 'Status', 
			width : 120,
			formatter : statusFormatter
		}

		] ],toolbar : [ {
			text : 'Add',
			iconCls : 'icon-add',
			handler : function() {
				$('#addDialog').dialog('open');
			}
		}, {
			text : 'Delete',
			iconCls : 'icon-cancel',
			handler : function() {
				var selected = $('#mainTable').datagrid('getSelected');
				if (selected){
					$.messager.confirm('Confirm', 'Are you confirm?', function(r){
						if (r){
							$('#delMOId').val(selected.moId);
							$('#delForm').submit(); 
						}
					});
				}
				
			}
		} ],
		pagination : true, //包含分页  
		singleSelect : true, 
		onDblClickRow:function(rowIndex){
					var row = $('#mainTable').datagrid('getSelected');
					$('#detailDialog').dialog('open');
					var queryParams = $('#detailTable').datagrid('options').queryParams;  
   					queryParams.detailMOId = row.moId;  //设置值
    				$('#detailTable').datagrid('options').queryParams=queryParams;        
    				$("#detailTable").datagrid('reload'); 
				}
	
	});
	
	$('#addDialog').dialog({
		title : 'New Material',
    	modal : true, 
    	closed : true
	}); 
	
	$('#detailDialog').dialog({
		title : 'Order Detail',
    	modal : true, 
    	closed : true
	}); 
	
	
	
	
	
	
	$('#detailTable').datagrid( {
		title : 'Bids',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getSBidListByMOId.action',
		sortName : 'moId',
		sortOrder : 'desc',
		idField : 'moId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'sbId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'sbBid',
			title : 'Price',
			width : 120
		}, {
			field : 'supplierName',
			title : 'Supplier',
			width : 120
		}, {
			field : 'sbStatus',
			title : 'Status', 
			width : 120,
			formatter : statusFormatter
		}

		] ],
		singleSelect : true,
		toolbar:[{
					text:'Select',
					iconCls:'icon-ok',
					handler:function(){
						var id;
						var price;
						var supplier;
						var supplierId;
						var status;
						var row = $('#detailTable').datagrid('getSelected');
						id = row.sbId;
						status = row.sbStatus;
						price = row.sbBid;
						supplier = row.supplierName;
						supplierId = row.sbSupplier;
						if(status!=1){
							$.messager.confirm('Confirm', 'Accept ('+id+')'+supplier+'\'s bid?<br/>Price:'+price, function(r){
							if (r){
								$('#editSBId').val(id);
								$('#editForm').submit();
								$('#detailDialog').dialog('close');
								$("#detailTable").datagrid('reload'); 
								$("#mainTable").datagrid('reload');
							}
							});
						}
						
						
						
					}
				}]
	
	});
	
	$('#addForm').form({  
    url:'../json/addMOrder.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
    		$('#addDialog').dialog('close');
        	$.messager.show({
				title:'Success!',
				msg:'Add a new bid successfully!',
				timeout:4000,
				showType:'slide'
			});
        	$('#addForm').form('clear');
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	$('#editForm').form({  
    url:'../json/acceptBid.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
        	$.messager.show({
				title:'Success!',
				msg:'Status change successful!',
				timeout:4000,
				showType:'slide'
			});
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	$('#delForm').form({  
    url:'../json/delMOrder.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
        	$.messager.show({
				title:'Success!',
				msg:'A bid be deleted!',
				timeout:4000,
				showType:'slide'
			});
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	
	
	
	
	$('#addMOMId').combobox({  
        url:'../json/getMaterialsListWithoutPage.action',  
        valueField:'mId',  
        textField:'mName'
    });  
	
	
	
});

function submitAddForm(){
	if(isNaN(document.addForm.moAmount.value)||document.addForm.moAmount.value<=0){
   		alert('Amount must be a positive number!')
   		document.addForm.mAmount.focus();
   		return false;
	}
	if(!document.addForm.moMId.value || !document.addForm.moAmount.value){
		alert('Please fill in the blank!');
		return false;
	}
	// submit the form  
	$('#addForm').submit();  	
};


var status = [
		    {sbStatus:'0',statusDisplay:''},
		    {sbStatus:'1',statusDisplay:'Selected'}
		];
function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].sbStatus == value) return status[i].statusDisplay;
			}
			return value;
};