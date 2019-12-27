function broadcast(componentName, eventName, params) {
  this.$children.forEach(child => {
    let name = child.$options.componentName;

    if (name === componentName) {
      child.$emit.apply(child, [eventName].concat(params));
    } else {
      broadcast.apply(child, [componentName, eventName].concat([params]));
    }
  });
}

export default {
  methods: {
    dispatch(componentName, eventName, params) {
      let parent = this.$parent || this.$root;
      let name = parent.$options.componentName;

      while (parent && (!name || name !== componentName)) {
        parent = parent.$parent;

        if (parent) {
          name = parent.$options.componentName;
        }
      }
      if (parent) {
        parent.$emit.apply(parent, [eventName].concat(params));
      }
    },
    dispatchRoot(componentName, eventName, params) {
      let parent = this.$parent || this.$root;

      let root;

      while (parent) {
        parent = parent.$parent;

        if (parent && componentName === parent.$options.componentName) {
          root = parent;
        }
      }
      if (root) {
        root.$emit.apply(root, [eventName].concat(params));
      }
    },
    broadcast(componentName, eventName, params) {
      broadcast.call(this, componentName, eventName, params);
    }
  }
};
