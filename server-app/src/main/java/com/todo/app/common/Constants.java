package com.todo.app.common;

public class Constants {

	public enum Status {
		PENDING(1, "Pending"), COMPLETED(2, "Completed");

		private Integer id;
		private String displayName;

		Status(Integer id, String displayName) {
			this.id = id;
			this.displayName = displayName;
		}

		public Integer getId() {
			return this.id;
		}

		public String getDisplayName() {
			return this.displayName;
		}
	}
}
