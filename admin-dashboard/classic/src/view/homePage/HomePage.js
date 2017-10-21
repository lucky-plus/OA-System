Ext.define('Admin.view.homePage.HomePage', {
    extend: 'Ext.container.Container',
    xtype: 'dashboard',
	height:Ext.Element.getViewportHeight()-1000,//必须设置高，否则无法使用border布局	
	viewModel : {type: 'homePageViewModel'},   
	requires: [
        'Ext.layout.container.Border',
    ],
	layout:'border',
	//cls:'x-body-new',
	margin: '30 30 30 30',
        items: [{
			//title: '今日安排',
			region:'north',
			height: '10%',
			margin: '0px 0px 10px 0px',
			bodyStyle: 'background:#f6f6f6;',
			body:{
				cls:'x-body-new'
			},
			xtype: 'panel',
			layout:{
				type:'hbox',
				align: 'stretch'
			},
			items: [{
				xtype:'button',
				width:'25%',
				ui:'',
				text: '<div style="color:white;font-size:14px;width:268px;height:98px;line-height:100px; background:url(resources/images/待审核流程个数.jpg);">'+
				                   '<span style="padding-left:60px">个待审核流程</span></div>',
				margin: '0px 10px 0px 0px',
			// listeners:{
						// click:function(){
							// document.location.href="#notice";  
						// }
					// }
			},{	xtype:'button',
				width:'25%',
				ui:'',
				text: '<div style="color:white;font-size:14px;width:268px;height:98px;line-height:100px; background:url(resources/images/待做任务个数.jpg);">'+
				                   '<span style="padding-left:60px">个待完成任务</span></div>',
				margin: '0px 10px 0px 0px',
			},{
				xtype:'button',
				width:'25%',
				ui:'',
				text: '<div style="color:white;font-size:14px;width:268px;height:98px;line-height:100px; background:url(resources/images/申请中流程个数.jpg);">'+
				                   '<span style="padding-left:60px">个流程申请中</span></div>',
				margin: '0px 10px 0px 0px',
			},{
				xtype:'button',
				width:'25%',
				ui:'',
				text: '<div style="color:white;font-size:14px;width:268px;height:98px;line-height:100px; background:url(resources/images/紧急通知.jpg);">'+
				                   '<span style="padding-left:60px">紧急通知</span></div>',
				margin: '0px 0px 0px 0px',
			}]
		},
		// {
		// region:'center',
		// margin: '0px 5px 5px 0px',
		// layout:{
			// type:'vbox',
			// align: 'stretch'
		// },
		// bodyStyle: 'background:#f6f6f6;',
		// items: [{
			// xtype: 'panel',
			// margin: '0px 0px 5px 0px',
			// height:'45%',
			// },{
			// xtype: 'panel',//快捷通道
			// margin: '0px 0px 0px 0px',
			// height:'50%',
			// layout:{
				// type:'hbox',
				// align: 'stretch'
			// },
			// items: [{
					// xtype: 'button', //或者xtype: 'component',				
					// text: '<div style="background:url(resources/images/flow.jpg); width:152px;height:134px;"></div>',
					// ui:'',
					// listeners:{
						// click:function(){
							// document.location.href="#notice";  
						// }
					// }
				// }, {
					// xtype: 'component', //或者xtype: 'component',  
					// width: 152, //图片宽度  
					// height: 134,
					// autoEl: {  
						// tag: 'img',    //指定为img标签  
						// src: 'resources/images/flow.jpg'    //指定url路径  
					// }	 
				// },{
				// xtype: 'panel',
				// width:'20%',
				// margin: '0px 5px 0px 5px',
			// }, {
				// xtype: 'panel',
				// width:'20%',
				// margin: '0px 5px 0px 5px',
			// }, {
				// xtype: 'panel',
				// width:'20%',
				// margin: '0px 10px 0px 5px',
			// }]
			// },		
			
			// ]
		// }
		,{
		//title: '今日安排',
		region:'east',
		width: '30%',
		margin: '10px 0x 0px 0px',
		xtype: 'panel',
	},{
		region:'center',
		margin: '10px 20px 0px 0px',
		xtype: 'panel',
		bodyStyle: 'background:#f6f6f6;',
		layout:{
			type:'vbox',
			align: 'stretch'
		},
		items:[{
			xtype: 'panel',
			title: '<div style="font-size:16px;color:#6F6F6F"><b>| 快捷通道</b></div>',
			header:{
			cls:'x-panel-header-new'
			},		
			margin: '0px 0px 10px 0px',
			height:'30%',
			layout:{
				type:'hbox',
				align: 'stretch'
			},
			items: [{
					xtype: 'button', //或者xtype: 'component',				
					text: '<div style="background:url(resources/images/flow.jpg); width:118px;height:110px;"></div>',
					width:'16%',
					margin: '0px 0px 0px 0px',
					ui:'',
					listeners:{
						click:function(){
							document.location.href="#notice";  
						}
					}
			}, {
				xtype: 'button', //或者xtype: 'component',				
					text: '<div style="background:url(resources/images/flow.jpg); width:118px;height:110px;"></div>',
					width:'16%',
					margin: '0px 0px 0px 0px',
					ui:'',
					listeners:{
						click:function(){
							document.location.href="#notice";  
						}
					}
			}, {
				xtype: 'panel',
				width:'16%',
				margin: '0px 0px 0px 0px',
			},{
				xtype: 'panel',
				width:'16%',
				margin: '0px 0px 0px 0px',
			},{
				xtype: 'panel',
				width:'16%',
				margin: '0px 0px 0px 0px',
			},{
				xtype: 'panel',
				width:'16%',
				margin: '0px 0px 0px 0px',
			}]
		},{
			title: '<div style="font-size:16px;color:#6F6F6F;"><b>| 公告通知</b></div>',
			header:{
			cls:'x-panel-header-new'
			},
			height:'49%',
			margin: '10px 0px 0px 0px',
			xtype: 'noticeWatchGrid',
		}]
	}

	]
});