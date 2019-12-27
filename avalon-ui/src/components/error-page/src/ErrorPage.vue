<template>
  <div class="global-no-data">
    <slot>
      <img v-if="type === 403 || type === '403'" class="global-no-data-picture"
           :style="{height:`${imageHeight}px`,width:`${imageWidth}px`}"
           src="../images/no_authority_img.png"/>
      <img v-else-if="type === 404 || type === '404'" class="global-no-data-picture"
           :style="{height:`${imageHeight}px`,width:`${imageWidth}px`}"
           src="../images/no_data_img.png"/>
      <img v-else-if="type === 500 || type === '500'" class="global-no-data-picture"
           :style="{height:`${imageHeight}px`,width:`${imageWidth}px`}"
           src="../images/no_page_img.png"/>
      <img v-else class="global-no-data-picture"
           :style="{height:`${imageHeight}px`,width:`${imageWidth}px`}"
           src="../images/no_data_img.png"/>
    </slot>
    <div class="global-no-data-text"
         :class="{'is-margin-top':imageWidth!==0 && imageHeight !== 0}">
      <slot name="title">
        <span>{{__title__}}</span>
      </slot>
    </div>
    <div class="global-no-data-button">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script>

  export default {

    name: `error-page`,

    componentName: `ErrorPage`,

    optionName: 'errorPage',

    props: {// noData 403 404 500
      type: {
        type: [String, Number],
        default: 'noData'
      },
      title: String,//标题
      imageWidth: {
        type: Number,
        default: 200
      },
      imageHeight: {
        type: Number,
        default: 200
      }
    },

    computed: {
      __title__() {
        if (this.title) {
          return this.title;
        }
        if (this.type === 403 || this.type === '403') {
          return '抱歉您无权查看此页面。';
        }
        if (this.type === 404 || this.type === '404') {
          return '抱歉，您访问的页面不存在。';
        }
        if (this.type === 500 || this.type === '500') {
          return '抱歉，您的网络有问题加载失败。';
        }
        return '抱歉，当前页面暂无信息。';
      }
    }
  }
</script>
