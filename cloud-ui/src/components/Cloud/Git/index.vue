<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'Github',
  data() {
    return {
      url: 'https://github.com/fank243/springcloud'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
