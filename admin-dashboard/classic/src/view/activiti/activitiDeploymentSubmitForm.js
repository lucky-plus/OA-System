/* 
* @Author: xgd
* @Date:   2017-10-22 22:07:22
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:16:20
*/

Ext.define('Admin.view.activiti.activitiDeploymentSubmitForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.activitiDeploymentSubmitForm',
	
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File'
    ],

    controller: 'activitiDeploymentGridViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    items: [{
        xtype: 'filefield',
        name: 'bushuwenjian',
        fieldLabel: 'bushuwenjian',
        labelWidth: 50,
        msgTarget: 'side',
        allowBlank: false,
		buttonText: '选择一个部署文件...'
	}],
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
            xtype: 'button',
            text: '提交',
            handler: 'activitiDeploymentSubmit'
        },{
            xtype: 'button',
            text: '取消',
            handler: 'activitiDeploymentSubmitClose'
        }]
    }
});