Ext.define('Admin.view.profile.FileUploadForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.fileUploadForm',
    imageWidth:300,
    notice:'',
    initComponent:function(){
        var me = this;
        Ext.apply(this,{
            //layout:'column',
            items:[
                {
                    xtype:'box',
                    width:me.imageWidth,
                    maxWidth:300,
                    reference:'imageShow',
                    autoEl:{
                        tag:'img',
                        // src:MoenSun.moensun.constant.Image.noPicture,
                       // onerror:"javascript:this.src='"+MoenSun.moensun.constant.Image.noPicture+"'"
                    }
                },
                {
                    xtype:'filefield',
                    buttonOnly:true,
                    buttonText: '选择图片',
                    listeners:{
                        change:me.changeSelect
                    }
                },
                {
                    xtype:'component',
                    html:me.notice
                },
                {
                    xtype:'hiddenfield',
                    name: me.name
                }
            ]
        });
        this.callParent();
    },
    changeSelect :function(fileFiled,value,eOpts){
        var me = this;
        var image = me.up().down('box').getEl().dom;
        var hidden = me.up().down('hiddenfield');
        var file = fileFiled.fileInputEl.dom.files.item(0);
        var fileReader = new FileReader(value);
        fileReader.readAsDataURL(file);
        fileReader.onload=function(e){
            image.src = e.target.result;
            hidden.setValue(e.target.result);
        }
        me.value = '';
    },
    getValue:function(){
        var me = this;
        var hidden = me.up().down('hiddenfield');
        return hidden.getValue();
    },
    bbar: {
    overflowHandler: 'menu',
    items: ['->',{
        xtype: 'button',
        text: '上传',
        handler: 'imageFileUpload'
    },{
        xtype: 'button',
        text: '取消',
        handler: 'profileGridWindowClose'
    }]
    }
});