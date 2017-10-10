Ext.define('Admin.view.main.MainController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.main',

    listen : {
        controller : {
            '#' : {
                unmatchedroute : 'onRouteChange'
            }
        }
    },

    routes: {
        ':node': 'onRouteChange'
    },

    lastView: null,

    setCurrentView: function(hashTag) {
        hashTag = (hashTag || '').toLowerCase();

        var me = this,
            refs = me.getReferences(),
            mainCard = refs.mainCardPanel,
            mainLayout = mainCard.getLayout(),
            navigationList = refs.navigationTreeList,
            store = navigationList.getStore(),
            node = store.findNode('routeId', hashTag) ||
                   store.findNode('viewType', hashTag),
            view = (node && node.get('viewType')) || 'page404',
            lastView = me.lastView,
            existingItem = mainCard.child('component[routeId=' + hashTag + ']'),
            newView;

        // Kill any previously routed window
        if (lastView && lastView.isWindow) {
            lastView.destroy();
        }

        lastView = mainLayout.getActiveItem();

        if (!existingItem) {
            newView = Ext.create({
                xtype: view,
                routeId: hashTag,  // for existingItem search later
                hideMode: 'offsets'
            });
        }

        if (!newView || !newView.isWindow) {
            // !newView means we have an existing view, but if the newView isWindow
            // we don't add it to the card layout.
            if (existingItem) {
                // We don't have a newView, so activate the existing view.
                if (existingItem !== lastView) {
                    mainLayout.setActiveItem(existingItem);
                }
                newView = existingItem;
            }
            else {
                // newView is set (did not exist already), so add it and make it the
                // activeItem.
                Ext.suspendLayouts();
                mainLayout.setActiveItem(mainCard.add(newView));
                Ext.resumeLayouts(true);
            }
        }

        navigationList.setSelection(node);

        if (newView.isFocusable(true)) {
            newView.focus();
        }

        me.lastView = newView;
    },

    onNavigationTreeSelectionChange: function (tree, node) {
        var to = node && (node.get('routeId') || node.get('viewType'));

        if (to) {
            this.redirectTo(to);
        }
    },

    onToggleNavigationSize: function () {
        var me = this,
            refs = me.getReferences(),
            navigationList = refs.navigationTreeList,
            wrapContainer = refs.mainContainerWrap,
            collapsing = !navigationList.getMicro(),
            new_width = collapsing ? 64 : 250;

        if (Ext.isIE9m || !Ext.os.is.Desktop) {
            Ext.suspendLayouts();

            refs.senchaLogo.setWidth(new_width);

            navigationList.setWidth(new_width);
            navigationList.setMicro(collapsing);

            Ext.resumeLayouts(); // do not flush the layout here...

            // No animation for IE9 or lower...
            wrapContainer.layout.animatePolicy = wrapContainer.layout.animate = null;
            wrapContainer.updateLayout();  // ... since this will flush them
        }
        else {
            if (!collapsing) {
                // If we are leaving micro mode (expanding), we do that first so that the
                // text of the items in the navlist will be revealed by the animation.
                navigationList.setMicro(false);
            }
            navigationList.canMeasure = false;

            // Start this layout first since it does not require a layout
            refs.senchaLogo.animate({dynamic: true, to: {width: new_width}});

            // Directly adjust the width config and then run the main wrap container layout
            // as the root layout (it and its chidren). This will cause the adjusted size to
            // be flushed to the element and animate to that new size.
            navigationList.width = new_width;
            wrapContainer.updateLayout({isRoot: true});
            navigationList.el.addCls('nav-tree-animating');

            // We need to switch to micro mode on the navlist *after* the animation (this
            // allows the "sweep" to leave the item text in place until it is no longer
            // visible.
            if (collapsing) {
                navigationList.on({
                    afterlayoutanimation: function () {
                        navigationList.setMicro(true);
                        navigationList.el.removeCls('nav-tree-animating');
                        navigationList.canMeasure = true;
                    },
                    single: true
                });
            }
        }
    },

    onMainViewRender:function() {
        if (!window.location.hash) {
            this.redirectTo("login");
        }
    },


    onRouteChange: function(id) {
        //登录校验:没有登录无法访问其他模块.
        var me = this;
        if (loginUser != "null" || id == "login") {
            me.setCurrentView(id);

          if(loadFlag == 0) {
            //动态加载菜单
            var rootTree = Ext.data.StoreManager.lookup('NavigationTree');
            
            rootTree.on(
              "load",function(){
                // Ext.Msg.alert('警告', 'aaaaaa!');
                this.getRoot().appendChild(
                  {
                    text: '信息中心',
                    iconCls: 'x-fa fa-leanpub',
                    expanded: false,
                    selectable: false,
                    //routeId: 'pages-parent',
                    //id: 'pages-parent',

                    children: [
                        {
                            text: '公告中心',
                            iconCls: 'x-fa fa-file-o',
                            viewType: 'notice',
                            leaf: true
                        },
                        {
                            text: '资源下载',
                            iconCls: 'x-fa  fa-arrow-circle-o-down',
                            viewType: 'resources',
                            leaf: true
                        },
                        {
                            text: '通讯录',
                            iconCls: 'x-fa fa-book ',
                            viewType: 'address',
                            leaf: true
                        }
                      ]
                  },
                  {
                        text: 'Profile',
                        iconCls: 'x-fa fa-user',
                        viewType: 'profile',
                        leaf: true
                  }
                );
              }
            );

            var modules = eval(loginUserModules);
            for(var i = 0; i < modules.length; i++) {
              var module = modules[i];
              if(module.modelName == "用户管理") {
                rootTree.on(
                    "load",function(){
                      // Ext.Msg.alert('警告', 'aaaaaa!');
                      this.getRoot().appendChild(
                        {
                            text: '用户管理',
                            iconCls: 'x-fa fa-leanpub',
                            expanded: false,
                            selectable: false,
                            //routeId: 'pages-parent',
                            id: 'roleManage',
                            children: [
                                {
                                    text: '角色管理',
                                    iconCls: 'x-fa fa-file-o',
                                    viewType: 'role',
                                    leaf: true
                                },
                                {
                                    text: '权限设置',
                                    iconCls: 'x-fa  fa-arrow-circle-o-down',
                                    viewType: 'authority',
                                    leaf: true
                                }
                            ]
                          }
                      );
                    }
                  );
              }
            }

              rootTree.reload();
              loadFlag = 1;
            }


        } else {
            Ext.Msg.alert('警告', '非法登录系统!', function() {
                me.setCurrentView('login');
            });
        }
    },

    onLogoutButton: function() {
        //注销，退出登录
        var me = this;
        Ext.Ajax.request({
            url: 'logoutAction',
            method: 'post',
            params: {
                userName: loginUser
            },
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if(json.success){
                    me.redirectTo('login', true);
                    window.location.reload();
                }else{
                    Ext.Msg.alert('操作失败。请重试', json.msg);
                }
            }
        });
    },

    onSearchRouteChange: function () {
        this.setCurrentView('searchresults');
    },

    onSwitchToModern: function () {
        Ext.Msg.confirm('Switch to Modern', 'Are you sure you want to switch toolkits?',
                        this.onSwitchToModernConfirmed, this);
    },

    onSwitchToModernConfirmed: function (choice) {
        if (choice === 'yes') {
            var s = window.location.search;

            // Strip "?classic" or "&classic" with optionally more "&foo" tokens
            // following and ensure we don't start with "?".
            s = s.replace(/(^\?|&)classic($|&)/, '').replace(/^\?/, '');

            // Add "?modern&" before the remaining tokens and strip & if there are
            // none.
            window.location.search = ('?modern&' + s).replace(/&$/, '');
        }
    },

    onEmailRouteChange: function () {
        this.setCurrentView('email');
    }
});
