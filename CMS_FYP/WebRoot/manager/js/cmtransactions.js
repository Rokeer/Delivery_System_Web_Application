$(function() {
	var lastIndex;
	$('#mainTable').datagrid( {
		title : 'Customer orders',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getCMTransactionsList.action', //action地址  
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
			field : 'clientName', 
			title : 'Client',
			width : 120
		}, {
			field : 'inventory',
			title : 'Inventory', 
			width : 130, 
			styler:function(value,row,index){
				if (value.substring(0,4) == 'Need'){
				return 'background-color:#ffee00;color:red;';
				} else if (value.substring(0,9) == 'Inventory') {
					return 'background-color:#6293BB;color:#fff;font-weight:bold;';
				}
			}
		}, {
			field : 'cmtStatus',
			title : 'Status', 
			width : 120, 
			formatter : statusFormatter, 
			editor : {type:'combobox',options:{valueField:'cmtStatus',textField:'statusDisplay',data:status,required:true}}
		}

		] ],
		toolbar:[{
					text:'Accept',
					iconCls:'icon-save',
					handler:function(){
					var ids = [];
					var statuses = [];
					var rows = $('#mainTable').datagrid('getChanges');
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].cmtId);
						statuses.push(rows[i].cmtStatus);
					}
					var cmtIds = ids.join(',');
					var cmtStatuses = statuses.join(',');
					
					$.messager.confirm('Change Status', 'Change transactions\' status?<br/>'+cmtIds, function(r){
						if (r){
							$('#editCMTIds').val(cmtIds);
							$('#editCMTStatuses').val(cmtStatuses);
							$('#editForm').submit();
						}
					});
						
					}
				},'-',{
					text:'Reject',
					iconCls:'icon-undo',
					handler:function(){
						$('#mainTable').datagrid('rejectChanges');
					}
				}],
		pagination : true, //包含分页  
		singleSelect : true,
		onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},
		onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						$('#mainTable').datagrid('endEdit', lastIndex);
						$('#mainTable').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
	});
	
	
	$('#editForm').form({  
    url:'../json/editCMTransactionStatus.action',  
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
        	$('#mainTable').datagrid('acceptChanges');
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
});

var status = [
		    {cmtStatus:'0',statusDisplay:'Not Confirm'},
		    {cmtStatus:'1',statusDisplay:'Confirmed'},
		    {cmtStatus:'2',statusDisplay:'Closed'}
		];
function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].cmtStatus == value) return status[i].statusDisplay;
			}
			return value;
};