<template>
  <div>
    <div v-for="item in brew" v-bind:key="item.id"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: [{ brew: [] }],
};
</script>

<style>
</style>