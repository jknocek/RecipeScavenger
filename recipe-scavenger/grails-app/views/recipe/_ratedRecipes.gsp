<style>
	.thumbFeatures .jshowoff-slidelinks a.jshowoff-active {
		border: 1px solid #fff;
		}
	.thumbFeatures .jshowoff-slidelink-0 {
	
		background-image:url(http://i-cdn.apartmenttherapy.com/uimages/kitchen/2008_04_15-PlaneFood.jpg);
		}
	.thumbFeatures .jshowoff-slidelink-1 {
		background-image: url(http://static.oprah.com/images/foodhome/food/200402/food_200402_pancakes_600x411.jpg);
		}
	.thumbFeatures .jshowoff-slidelink-2 {
		background-image: url(http://graphics8.nytimes.com/images/2010/07/26/health/26consults_food/26consults_food-blogSpan.jpg);
		}
	.thumbFeatures .jshowoff-slidelink-3 {
		background-image: url(http://www.lexisnexis.com/Community/international-foreignlaw/cfs-filesystemfile.ashx/__key/CommunityServer.Components.SiteFiles/Images.I+_2600_+FL+Images/food_5F00_12052669.jpg);
		}
</style>

<div class="content-form"  style="margin: 10px auto;">
	<g:form controller="recipe">
		<div class="features"  style="margin: 10px auto;">
			<div title="${recipes[0]}"><img src="http://i-cdn.apartmenttherapy.com/uimages/kitchen/2008_04_15-PlaneFood.jpg" alt="Shore" /></div>
			<div title="${recipes[1]}"><img src="http://static.oprah.com/images/foodhome/food/200402/food_200402_pancakes_600x411.jpg" /></div>	
			<div title="${recipes[2]}"><img src="http://graphics8.nytimes.com/images/2010/07/26/health/26consults_food/26consults_food-blogSpan.jpg" /></div>
			<div title="${recipes[3]}"><img src="http://www.lexisnexis.com/Community/international-foreignlaw/cfs-filesystemfile.ashx/__key/CommunityServer.Components.SiteFiles/Images.I+_2600_+FL+Images/food_5F00_12052669.jpg" /></div>
		</div>
	</g:form>
</div>
<div id="accordion">
	<h3 style="text-align: center;"><a href="#">1. ${recipes[0]}</a></h3>
	<div title="${recipes[0]}"></div>
	<h3 style="text-align: center;"><a href="#">2. ${recipes[1]}</a></h3>
	<div title="${recipes[1]}"></div>
	<h3 style="text-align: center;"><a href="#">3. ${recipes[2]}</a></h3>
	<div title="${recipes[2]}"></div>
	<h3 style="text-align: center;"><a href="#">4. ${recipes[3]}</a></h3>
	<div title="${recipes[3]}"></div>
</div>