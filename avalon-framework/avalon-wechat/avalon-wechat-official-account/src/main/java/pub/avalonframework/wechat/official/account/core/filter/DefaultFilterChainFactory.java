package pub.avalonframework.wechat.official.account.core.filter;

import pub.avalonframework.wechat.official.account.core.filter.impl.DefaultSubscribe;
import pub.avalonframework.wechat.official.account.core.filter.impl.DefaultTextFilter;
import pub.avalonframework.wechat.official.account.core.filter.impl.DefaultUnSubscribe;

public class DefaultFilterChainFactory implements FilterChainFactory {

	@Override
	public FilterChain buildTextFilterChain() {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new DefaultTextFilter());
		return filterChain;
	}

	@Override
	public FilterChain buildImageFilterChain() {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new DefaultTextFilter());
		return filterChain;
	}

	@Override
	public FilterChain buildLocationFilterChain() {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new DefaultTextFilter());
		return filterChain;
	}

	@Override
	public FilterChain buildLinkFilterChain() {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new DefaultTextFilter());
		return filterChain;
	}

	@Override
	public FilterChain buildVoiceFilterChain() {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new DefaultTextFilter());
		return filterChain;
	}

	@Override
	public FilterChain buildEventFilterChain() {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new DefaultSubscribe());
		filterChain.addFilter(new DefaultUnSubscribe());
		filterChain.addFilter(new DefaultTextFilter());
		return filterChain;
	}
}