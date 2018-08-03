<!-- Scripts -->
<script src="/resources/js/sidebar.js"></script>

<div style="float: left;" class="fixed">
	<div class="sidebar" id="sidebar">
		<section id="sidebar-contents">
			<h3 style="color: white;">This would be an overview of all
				notifications</h3>
			<div id="newsfeed">
				<div id="news-wrapper">
					<div class="col-xs-4"
						style="padding: 0; margin: 10px; margin-bottom: 15px;">
						<img src="/resources/images/placeholder.jpg" class="news-thumb">
					</div>
					<div class="col-xs-6 news-text">
						<h6>[Headline]</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Aliquam vestibulum, magna eget commodo aliquam, diam</p>
					</div>
				</div>
				<div id="news-wrapper">
					<div class="col-xs-4"
						style="padding: 0; margin: 10px; margin-bottom: 15px;">
						<img src="/resources/images/placeholder.jpg" class="news-thumb">
					</div>
					<div class="col-xs-6 news-text">
						<h6>[Headline]</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Aliquam vestibulum, magna eget commodo aliquam, diam</p>
					</div>
				</div>

			</div>
			<div id="mailbox">
				<div id="message-wrapper">
					<div class="col-xs-8 news-text">
						<h6>[Subject]</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Aliquam vestibulum, magna eget commodo aliquam, diam</p>
					</div>
					<div class="col-xs-3"
						style="padding: 0; margin: 10px; margin-bottom: 15px;">
						<img src="/resources/images/badge.jpg" class="profile-pic">
					</div>

				</div>
			</div>

			<div id="notifications">
				<!-- Dynamic code needed -->
				<div id="note-wrapper">
					<p>[Course-title/message/system note]</p>
					<h6>
						<strong>[Heading]</strong>
					</h6>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Aliquam vestibulum, magna eget commodo aliquam, diam nisi interdum
						est, non molestie odio ipsum vel magna. Curabitur tincidunt
						maximus dolor, ut tincidunt augue. Vivamus ipsum orci, dictum vel
						eleifend id, egestas eu quam. Fusce fermentum lorem dolor.</p>
				</div>
				<div id="note-wrapper">
					<p>[Course-title/message/system note]</p>
					<h6>
						<strong>[Heading]</strong>
					</h6>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Aliquam vestibulum, magna eget commodo aliquam, diam nisi interdum
						est, non molestie odio ipsum vel magna. Curabitur tincidunt
						maximus dolor, ut tincidunt augue. Vivamus ipsum orci, dictum vel
						eleifend id, egestas eu quam. Fusce fermentum lorem dolor.
						Praesent dapibus massa ac est maximus, non finibus urna mollis. Ut
						mollis ultricies mauris. Ut congue tellus lorem.</p>
				</div>
				<div id="note-wrapper">
					<p>[Course-title/message/system note]</p>
					<h6>
						<strong>[Heading]</strong>
					</h6>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Aliquam vestibulum, magna eget commodo aliquam, diam nisi interdum
						est, non molestie odio ipsum vel magna. Curabitur tincidunt
						maximus dolor, ut tincidunt augue. Vivamus ipsum orci, dictum vel
						eleifend id, egestas eu quam.</p>
				</div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>
				<div id="note-wrapper"></div>

			</div>

			<div id="friends-pane">
				<c:forEach items="${friends}" var="friend">
					<div id="message-wrapper">
						<div class="col-xs-8 news-text">
							<h6>${friend.getLearner().getName()}</h6>
							<p>
								<a href="/learner/profile/${friend.getId()}">Profile</a>
							</p>
						</div>
						<div class="col-xs-3"
							style="padding: 0; margin: 10px; margin-bottom: 15px;">
							<img src="/resources/images/badge.jpg" class="profile-pic">
						</div>

					</div>
				</c:forEach>
			</div>

		</section>
		<div id="sidebar-icons">

			<a><i id="toggle-sidebar-button"
				class="fa fa-navicon sidebar-icon fa-2x"
				style="left: 13px; top: 10px; position: absolute;"></i></a>
			<!-- Will be hollow bell (bell-o) if no notifications, filled (bell) if notifications -->
			<a><i id="bell" class="fa fa-bell-o sidebar-icon fa-2x"
				style="left: 10px; top: 60px; position: absolute;"></i></a>
			<!-- Again filled for unread mail -->
			<a><i id="mail" class="fa fa-envelope-o sidebar-icon fa-2x"
				style="left: 10px; top: 120px; position: absolute;"></i></a> <a><i
				id="news" class="fa fa-newspaper-o sidebar-icon fa-2x"
				style="left: 10px; top: 180px; position: absolute;"></i></a> <a><i
				id="friends" class="fa fa-users sidebar-icon fa-2x"
				style="left: 10px; top: 240px; position: absolute;"></i></a> <a
				href="/learner/profile/"> <i id="profile"
				class="fa fa-user sidebar-icon fa-2x"
				style="left: 10px; top: 300px; position: absolute;"></i></a>
		</div>
	</div>
</div>
